package com.wypychmat.clientservice.domain;

public class Client {

    private int id;
    private String name;
    private Discount discount;
    private int actualLevelPointCounter;

    public Client() {
    }

    public Client(String name, Discount discount, int actualLevelPointCounter) {
        this.name = name;
        this.discount = discount;
        this.actualLevelPointCounter = actualLevelPointCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public int getActualLevelPointCounter() {
        return actualLevelPointCounter;
    }

    public void setActualLevelPointCounter(int actualLevelPointCounter) {
        this.actualLevelPointCounter = actualLevelPointCounter;
    }
}
