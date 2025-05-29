package com.pluralsight.topping;

public class PremiumTopping extends Topping {
    public PremiumTopping(String name) {
        super(name);
    }

    @Override
    public double getPrice(String size) {
        return 0.00;
    }
}