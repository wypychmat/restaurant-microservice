package com.wypychmat.orders.domain;

import java.util.List;

public class Order {
    private int id;
    private int userId;
    private double finalPrice;
    private List<Dish> dishes;

    public Order() {
    }

    public Order(int userId, List<Dish> dishes) {
        this.userId = userId;
        this.dishes = dishes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
