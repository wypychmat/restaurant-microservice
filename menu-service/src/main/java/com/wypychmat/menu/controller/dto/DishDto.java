package com.wypychmat.menu.controller.dto;

public class DishDto {
    private String name;
    private Double price;

    public DishDto() {
    }

    public DishDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
