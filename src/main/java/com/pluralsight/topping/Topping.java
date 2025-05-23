package com.pluralsight.topping;

public abstract class Topping {
    protected String name;

    public Topping(String name) {
        this.name = name;
    }

    public abstract double getPrice(String size);
}