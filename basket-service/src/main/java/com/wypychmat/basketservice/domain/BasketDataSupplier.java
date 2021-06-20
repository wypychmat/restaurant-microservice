package com.wypychmat.basketservice.domain;

import java.util.Optional;

public interface BasketDataSupplier {
    Optional<Basket> getBasket(Integer userId);

    double addToBasket(Integer userId, Integer dishId);

    Double buy(Integer userId);
}
