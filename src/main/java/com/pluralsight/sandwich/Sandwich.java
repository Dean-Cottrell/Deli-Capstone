package com.pluralsight.sandwich;

import com.pluralsight.topping.Topping;
import com.pluralsight.topping.RegularTopping;
import com.pluralsight.topping.PremiumTopping;
import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String size;
    private String breadType;
    private boolean toasted;
    private List<PremiumTopping> premiumToppings;
    private List<RegularTopping> regularToppings;

    public Sandwich(String size, String breadType, List<Topping> toppings) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = false;
        premiumToppings = new ArrayList<>();
        regularToppings = new ArrayList<>();

        for (Topping topping : toppings) {
            if (isPremium(topping.getName())) {
                premiumToppings.add((PremiumTopping) topping);
            } else {
                regularToppings.add((RegularTopping) topping);
            }
        }
    }

    private boolean isPremium(String name) {
        return List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon",
                "American", "Provolone", "Cheddar", "Swiss").contains(name);
    }

    public double calculateTotal() {
        double basePrice = switch (size) {
            case "4" -> 4.99;
            case "8" -> 7.99;
            case "12" -> 10.99;
            default -> 0;
        };
        double toppingPrice = premiumToppings.stream().mapToDouble(t -> t.getPrice(size)).sum() +
                regularToppings.stream().mapToDouble(t -> t.getPrice(size)).sum();
        return basePrice + toppingPrice;
    }

    public String details() {
        return "Size: " + size + " inches\n" +
                "Bread: " + breadType + "\n" +
                "Toasted: " + (toasted ? "Yes" : "No") + "\n" +
                "Premium Toppings: " + formatToppings(premiumToppings) + "\n" +
                "Regular Toppings: " + formatToppings(regularToppings) + "\n" +
                "Total: $" + String.format("%.2f", calculateTotal());
    }

    private String formatToppings(List<? extends Topping> toppings) {
        return toppings.isEmpty() ? "None" : String.join(", ", toppings.stream().map(Topping::getName).toList());
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }
}