package com.wypychmat.orders.repository;

import com.wypychmat.orders.domain.Order;
import com.wypychmat.orders.domain.OrdersRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class OrdersRepositoryImpl implements OrdersRepository {

    private final Map<Integer, Order> orders;


    public OrdersRepositoryImpl() {
        orders = new ConcurrentHashMap<>();
    }

    @Override
    public void saveOrder(Order order) {
        Integer id = orders.values().stream()
                .map(Order::getId)
                .map(o -> ++o)
                .max(Integer::compareTo)
                .orElse(1);
        order.setId(id);
        orders.put(id, order);
    }

    @Override
    public List<Order> getOrdersByClientId(Integer id) {

        return orders.values()
                .stream()
                .filter(o -> id.equals(o.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Optional<Order> getOrder(Integer id) {
        Order order = orders.get(id);
        if(order != null){
            return Optional.of(order);
        }
        return Optional.empty();
    }
}
