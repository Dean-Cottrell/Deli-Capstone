package com.pluralsight.order;

import java.util.ArrayList;
import java.util.List;
import com.pluralsight.sandwich.Sandwich;
import com.pluralsight.addons.Drink;
import com.pluralsight.addons.Chips;

public class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;

    public Order() {
        this.orderId = nextOrderId++;
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
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
            total += s.calculateTotal();
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
        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("Order ID: ").append(getOrderId()).append("\n");
        orderSummary.append("Sandwiches:\n");
        for (Sandwich s : sandwiches) {
            orderSummary.append(s.details()).append("\n");
        }
        orderSummary.append("Drinks:\n");
        for (Drink d : drinks) {
            orderSummary.append(d.details()).append("\n");
        }
        orderSummary.append("Chips:\n");
        for (Chips c : chips) {
            orderSummary.append(c.details()).append("\n");
        }
        orderSummary.append("Total Price: $").append(calculateTotal());
        return orderSummary.toString();
    }
}