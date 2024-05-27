package edu.homework.orderpizza.entity;

public enum PizzaSize {
    small(0.5),
    normal(1),
    big(1.5);

    private double priceMultiplier;

    PizzaSize(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }
}