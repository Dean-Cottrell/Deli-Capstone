package com.pluralsight;

public class Meat extends Toppings {
    public Meat(String name) {
        super(name);

    }

    public double getPrice(String size) {
        return switch (size) {
            case "4" -> 1.00;
            case "8" -> 2.00;
            case "12" -> 3.00;
            default -> -1;
        };
    }
}
