package com.pluralsight.topping;

public class ExtraMeat extends PremiumTopping {
    public ExtraMeat(String name) {
        super(name);
    }

    @Override
    public double getPrice(String size) {
        return switch (size) {
            case "4" -> 0.50;
            case "8" -> 1.00;
            case "12" -> 1.50;
            default -> 0.00;
        };
    }
}