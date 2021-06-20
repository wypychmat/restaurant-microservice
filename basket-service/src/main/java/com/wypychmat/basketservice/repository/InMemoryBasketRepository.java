package com.wypychmat.basketservice.repository;

import com.wypychmat.basketservice.domain.Basket;
import com.wypychmat.basketservice.domain.BasketRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBasketRepository implements BasketRepository {

    private final Map<Integer, Basket> baskets;

    public InMemoryBasketRepository() {
        baskets = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Basket> getBasket(int userId) {
        Basket basket = baskets.get(userId);
        if (basket == null) {
            return Optional.empty();
        }
        return Optional.of(basket);
    }

    @Override
    public void addOrUpdateBasket(int userId, Basket basket) {
        baskets.put(userId, basket);
    }

    @Override
    public void clearBasketForClient(Integer userId) {
        baskets.remove(userId);
    }
}
