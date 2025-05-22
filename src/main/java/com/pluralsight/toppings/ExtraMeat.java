package com.pluralsight;

public class ExtraMeat extends Toppings {
    public ExtraMeat(String name) {
        super(name);

    }

    public double getPrice(String size) {
        return switch (size) {
            case "4" -> .50;
            case "8" -> 1.00;
            case "12" -> 1.50;
            default -> -1;
        };
    }
}
