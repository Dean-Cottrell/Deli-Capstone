package com.pluralsight;

public abstract class Toppings {
    private String name;
    public Toppings (String name){
        this.name = name;
    }
public abstract double getPrice(String size);

}
