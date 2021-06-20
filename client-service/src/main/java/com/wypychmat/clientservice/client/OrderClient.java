package com.wypychmat.clientservice.client;

import com.wypychmat.clientservice.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("order-service")
public interface OrderClient {

    @GetMapping("/orders/client/{id}")
    List<Order> getOrdersByClient(@PathVariable("id") Integer id);

}
