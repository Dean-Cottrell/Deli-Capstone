package com.pluralsight;

import java.util.List;

public class Sandwich {
    private String size;
    private String bread;
    private List<Topping> toppings;
    private boolean toasted;
    private boolean extraMeat;
    private boolean extraCheese;

    public Sandwich(String size, String bread, List<Topping> toppings, boolean toasted, boolean extraMeat, boolean extraCheese) {
        this.size = size;
        this.bread = bread;
        this.toppings = toppings;
        this.toasted = toasted;
        this.extraMeat = extraMeat;
        this.extraCheese = extraCheese;
    }

    public double calculatePrice() {
        double sandwichPrice = switch (this.size) {
            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> -1;
        };

        for (Topping t : this.toppings) {
            sandwichPrice += t.getPrice(this.size);
        }

        if (extraMeat) sandwichPrice += switch (this.size) {
            case "4" -> 0.50;
            case "8" -> 1.00;
            case "12" -> 1.50;
            default -> 0.00;
        };

        if (extraCheese) sandwichPrice += switch (this.size) {
            case "4" -> 0.30;
            case "8" -> 0.60;
            case "12" -> 0.90;
            default -> 0.00;
        };

        return sandwichPrice;
    }
    public String details() {
        StringBuilder s = new StringBuilder();
        s.append(toasted ? "Toasted " : "");
        s.append(size).append("\" sandwich on ").append(bread).append(" bread\n");

        for (Topping t : toppings) {
            s.append("  - ").append(t.getName()).append("\t$").append(t.getPrice(size)).append("\n");
        }

        if (extraMeat) s.append("  + Extra Meat\n");
        if (extraCheese) s.append("  + Extra Cheese\n");

        s.append("Total Price: $").append(calculatePrice());
        return s.toString();

    }
}
