package com.wypychmat.orders.service;

import com.wypychmat.orders.domain.Basket;
import com.wypychmat.orders.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Basket basket);

    List<Order> getOrderByClient(Integer id);

    List<Order> getOrders();

    Optional<Order> getOrder(Integer id);
}
