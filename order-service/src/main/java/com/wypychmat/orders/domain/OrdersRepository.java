package com.wypychmat.orders.domain;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository {
    void saveOrder(Order order);

    List<Order> getOrdersByClientId(Integer id);

    List<Order> getOrders();

    Optional<Order> getOrder(Integer id);
}
