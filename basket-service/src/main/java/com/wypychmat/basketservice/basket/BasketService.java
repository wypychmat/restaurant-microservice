package com.wypychmat.basketservice.basket;

import com.wypychmat.basketservice.domain.Basket;
import com.wypychmat.basketservice.domain.BasketRepository;
import com.wypychmat.basketservice.domain.Dish;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class BasketService {

    private final BasketRepository basketRepository;

    BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public Optional<Basket> getBasket(int userId) {
        return basketRepository.getBasket(userId);
    }

    public double addToBasket(int userid, Dish dish) {
        Optional<Basket> optionalBasket = basketRepository.getBasket(userid);
        Basket basket;
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
            basket.addDish(dish);
        } else {
            basket = new Basket(userid, dish);
        }
        double total = calculateNewTotal(basket);
        basketRepository.addOrUpdateBasket(userid, basket);
        return total;
    }

    private double calculateNewTotal(Basket basket) {
        double total = basket.getDishes().stream().map(Dish::getPrice).reduce(Double::sum).orElse(0d);
        basket.setTotal(total);
        return total;
    }
}
