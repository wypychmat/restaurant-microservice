package com.wypychmat.basketservice.client;

import com.wypychmat.basketservice.domain.Dish;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "menu-service")
public interface MenuClient {

    @GetMapping("/menus/{customerId}")
    ResponseEntity<Dish> getDish(@PathVariable("customerId") Integer customerId);
}
