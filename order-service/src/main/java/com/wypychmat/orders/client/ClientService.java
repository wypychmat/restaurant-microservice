package com.wypychmat.orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("client-service")
public interface ClientService {

    @GetMapping("/clients/discount/{id}")
    ResponseEntity<Double> getClientDiscount(@PathVariable("id") Integer id);


    @PutMapping("/clients/discount/{id}")
    void increaseDiscountCounter(@PathVariable("id") Integer id);
}
