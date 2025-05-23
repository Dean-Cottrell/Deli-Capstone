package com.pluralsight;

public abstract class Topping {  // Changed from Toppings to Topping
    protected String name;  // Changed private to protected

    public Topping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice(String size);
}