package com.pluralsight;

public class ExtraCheese extends Toppings {
    public ExtraCheese(String name) {
        super(name);

    }

    public double getPrice(String size) {
        return switch (size) {
            case "4" -> .30;
            case "8" -> .60;
            case "12" -> .90;
            default -> -1;
        };
    }
}
