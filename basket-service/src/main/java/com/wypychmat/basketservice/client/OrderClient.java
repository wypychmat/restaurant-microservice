package com.wypychmat.basketservice.client;

import com.wypychmat.basketservice.domain.Basket;
import com.wypychmat.basketservice.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "order-service")
public interface OrderClient {

    @PostMapping("/orders")
    Order createOrder(@RequestBody Basket basket);
}
