package com.pluralsight.topping;

public class Cheese extends RegularTopping {
    public Cheese(String name) {
        super(name);
    }

    @Override
    public double getPrice(String size) {
        return switch (size) {
            case "4" -> 0.75;
            case "8" -> 1.50;
            case "12" -> 2.25;
            default -> 0.00;
        };
    }
}