package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(Chips chip) {
        chips.add(chip);
    }

    public double calculateTotal() {
        double total = 0;

        for (Sandwich s : sandwiches) {
            total += s.calculatePrice();
        }

        for (Drink d : drinks) {
            total += d.getPrice();
        }

        for (Chips c : chips) {
            total += c.getPrice();
        }

        return total;
    }

    public String details() {
        StringBuilder orderSummary = new StringBuilder("Order Summary:\n");

        for (Sandwich s : sandwiches) {
            orderSummary.append(s.details()).append("\n");
        }

        for (Drink d : drinks) {
            orderSummary.append(d.details()).append("\n");
        }

        for (Chips c : chips) {
            orderSummary.append(c.details()).append("\n");
        }

        orderSummary.append("Total Price: $").append(calculateTotal());
        return orderSummary.toString();
    }
}