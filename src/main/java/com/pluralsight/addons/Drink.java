package com.pluralsight;

public class Drink {
    private String size;
    private String type;

    public Drink(String size, String type) {
        this.size = size;
        this.type = type;
    }

    public double getPrice() {
        return switch (size) {
            case "4" -> 2.00;
            case "8" -> 2.50;
            case "12" -> 3.00;
            default -> 0.00;
        };
    }

    public String details() {
        return size + " oz " + type + " - $" + getPrice();
    }
}