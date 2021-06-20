package com.wypychmat.clientservice.domain;

public enum Discount {
    noDiscount(3, 0.0),
    minDiscount(10, 0.05),
    basicDiscount(10, 0.08),
    regularDiscount(50, 0.10),
    extraDiscount(50, 0.15),
    vipDiscount(75, 0.20),
    masterDiscount(100, 0.25);

    private final int pointsToReach;
    private final double discount;

    Discount(int pointsToReach, double discount) {
        this.pointsToReach = pointsToReach;
        this.discount = discount;
    }

    public int getPointsToReach() {
        return pointsToReach;
    }

    public double getDiscount() {
        return discount;
    }
}
