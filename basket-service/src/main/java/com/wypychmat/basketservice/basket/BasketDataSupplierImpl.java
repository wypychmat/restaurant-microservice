package com.wypychmat.basketservice.basket;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.wypychmat.basketservice.client.MenuClient;
import com.wypychmat.basketservice.client.OrderClient;
import com.wypychmat.basketservice.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class BasketDataSupplierImpl implements BasketDataSupplier {
    private final Logger log = LoggerFactory.getLogger(BasketDataSupplierImpl.class);

    private final BasketService basketService;
    private final MenuClient menuClient;
    private final OrderClient orderClient;
    private final BasketRepository basketRepository;
    private final ResilienceService resilienceService;
    private final Cache<Integer, Optional<Basket>> cache = Caffeine
            .newBuilder()
            .expireAfterWrite(Duration.ofHours(24))
            .removalListener(new CacheRemovalListener<>())
            .build();


    public BasketDataSupplierImpl(BasketService basketService, MenuClient menuClient, OrderClient orderClient, BasketRepository basketRepository) {
        this.basketService = basketService;
        this.menuClient = menuClient;
        this.orderClient = orderClient;
        this.basketRepository = basketRepository;
        resilienceService = new ResilienceService(
                LoggerFactory.getLogger(BasketDataSupplierImpl.class),
                "Create Order");
    }


    @Override
    public Optional<Basket> getBasket(Integer userId) {
        Optional<Basket> basket = cache.get(userId, basketService::getBasket);
        if (basket.isEmpty()) {
            cache.invalidate(userId);
        }
        return basket;
    }

    @Override
    public double addToBasket(Integer userId, Integer dishId) {
        ResponseEntity<Dish> dish = menuClient.getDish(dishId);
        Dish body = dish.getBody();
        if (body != null) {
            cache.invalidate(userId);
            return basketService.addToBasket(userId, body);
        }
        return 0;
    }

    @Override
    public Double buy(Integer userId) {
        cache.invalidate(userId);
        return basketRepository
                .getBasket(userId)
                .map(value -> resilienceService.doWithSupplier(
                        getBuySupplier(userId, value),
                        e -> 0.0d))
                .orElse(0d);
    }

    private Supplier<Double> getBuySupplier(Integer userId, Basket value) {
        return () -> {
            Order order = orderClient.createOrder(value);
            basketRepository.clearBasketForClient(userId);
            return order.getFinalPrice();
        };
    }
}
