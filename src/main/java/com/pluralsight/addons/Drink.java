package com.pluralsight.addons;

public class Drink {
    private String size;
    private String type;

    public Drink(String size, String type) {
        this.size = size;
        this.type = type;
    }

    public double getPrice() {
        return switch (size) {
            case "Small" -> 2.00;
            case "Medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.00;
        };
    }

    public String details() {
        return size + " oz " + type + " - $" + getPrice();
    }
}