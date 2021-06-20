package com.wypychmat.basketservice.domain;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private int userId;
    private final List<Dish> dishes = new ArrayList<>();
    private Double total;

    public Basket() {
    }

    public Basket(int userId, Dish dish) {
        this.userId = userId;
        dishes.add(dish);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
