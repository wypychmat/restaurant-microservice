package com.wypychmat.basketservice.domain;

import java.util.Optional;

public interface BasketRepository {

    Optional<Basket> getBasket(int userId);

    void addOrUpdateBasket(int userId, Basket basket);

    void clearBasketForClient(Integer userId);
}
