package com.pluralsight.topping;

public class ExtraCheese extends PremiumTopping {
    public ExtraCheese(String name) {
        super(name);
    }

    @Override
    public double getPrice(String size) {
        return switch (size) {
            case "4" -> 0.30;
            case "8" -> 0.60;
            case "12" -> 0.90;
            default -> 0.00;
        };
    }
}