package com.wypychmat.basketservice.controller;

import com.wypychmat.basketservice.domain.Basket;
import com.wypychmat.basketservice.domain.BasketDataSupplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/baskets")
public class BasketController {

    private final BasketDataSupplier basketDataSupplier;

    public BasketController(BasketDataSupplier basketDataSupplier) {
        this.basketDataSupplier = basketDataSupplier;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Basket> getBasketForUser(@PathVariable Integer userId) {
        Optional<Basket> basket = basketDataSupplier.getBasket(userId);
        return basket
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.ok().build());
    }


    @PostMapping("/{userId}")
    public Double addToBasket(@PathVariable Integer userId, @RequestParam Integer dishId) {
        return basketDataSupplier.addToBasket(userId, dishId);
    }

    @PostMapping("/buy")
    public Double proceedWithBasket(@RequestParam Integer userId) {
        return basketDataSupplier.buy(userId);
    }
}
