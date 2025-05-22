package com.pluralsight;

public class Cheese extends Toppings {
    public Cheese(String name) {
        super(name);

    }

    public double getPrice(String size) {
        return switch (size) {
            case "4" -> 0.75;
            case "8" -> 1.50;
            case "12" -> 2.25;
            default -> -1;
        };
    }
}
