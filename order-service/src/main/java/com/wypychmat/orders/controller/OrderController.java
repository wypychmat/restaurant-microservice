package com.wypychmat.orders.controller;

import com.wypychmat.orders.domain.Basket;
import com.wypychmat.orders.domain.Order;
import com.wypychmat.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Basket basket) {
        return orderService.createOrder(basket);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer id) {
        return orderService.getOrder(id).map(ResponseEntity::ok).orElse(ResponseEntity.ok().build());
    }


    @GetMapping("/client/{id}")
    public List<Order> getOrdersByClient(@PathVariable Integer id) {
        return orderService.getOrderByClient(id);
    }


}
