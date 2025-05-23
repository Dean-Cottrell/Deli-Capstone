package com.pluralsight;

public class Chips {
    private String type;

    public Chips(String type) {
        this.type = type;
    }

    public double getPrice() {
        return 1.50;
    }

    public String details() {
        return type + " Chips - $1.50";
    }
}