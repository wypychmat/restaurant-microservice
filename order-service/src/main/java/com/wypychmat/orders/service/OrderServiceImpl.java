package com.wypychmat.orders.service;

import com.wypychmat.orders.client.ClientService;
import com.wypychmat.orders.domain.Basket;
import com.wypychmat.orders.domain.Dish;
import com.wypychmat.orders.domain.Order;
import com.wypychmat.orders.domain.OrdersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrdersRepository ordersRepository;
    private final ClientService clientService;

    public OrderServiceImpl(OrdersRepository ordersRepository, ClientService clientService) {
        this.ordersRepository = ordersRepository;
        this.clientService = clientService;
    }

    @Override
    public Order createOrder(Basket basket) {
        Order order = new Order(basket.getUserId(), basket.getDishes());
        ResponseEntity<Double> clientDiscount = clientService.getClientDiscount(basket.getUserId());
        Double discount = clientDiscount.getBody();
        if (discount == null)
            discount = 0.0;
        order.setFinalPrice(calculateOrderTotal(discount, basket.getDishes()));
        ordersRepository.saveOrder(order);
        increaseDiscount(basket.getUserId());
        return order;
    }

    private void increaseDiscount(int userId) {
        clientService.increaseDiscountCounter(userId);
    }

    @Override
    public List<Order> getOrderByClient(Integer id) {
        return ordersRepository.getOrdersByClientId(id);
    }

    @Override
    public List<Order> getOrders() {
        return ordersRepository.getOrders();
    }

    @Override
    public Optional<Order> getOrder(Integer id) {
        return ordersRepository.getOrder(id);
    }


    private double calculateOrderTotal(double discount, List<Dish> dishes) {
        Double total = dishes.stream().map(Dish::getPrice).reduce(Double::sum).orElse(0.0);
        return total * (1 - discount);
    }
}
