package com.pluralsight.sandwich;

import com.pluralsight.topping.Topping;
import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String size;
    private String breadType;
    private boolean toasted;
    private List<Topping> meats;
    private List<Topping> cheeses;
    private List<Topping> sauces;

    public Sandwich(String size, String breadType, List<Topping> toppings) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = false;
        meats = new ArrayList<>();
        cheeses = new ArrayList<>();
        sauces = new ArrayList<>();
        for (Topping topping : toppings) {
            if (isMeat(topping.getName())) {
                meats.add(topping);
            } else if (isCheese(topping.getName())) {
                cheeses.add(topping);
            } else if (isSauce(topping.getName())) {
                sauces.add(topping);
            }
        }
    }

    private boolean isMeat(String name) {
        return List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon").contains(name);
    }

    private boolean isCheese(String name) {
        return List.of("American", "Provolone", "Cheddar", "Swiss").contains(name);
    }

    private boolean isSauce(String name) {
        return List.of("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette").contains(name);
    }

    public double calculateTotal() {
        double basePrice = switch (size) {
            case "4" -> 4.99;
            case "8" -> 7.99;
            case "12" -> 10.99;
            default -> 0;
        };
        double toppingPrice = (meats.size() * 1.50) + (cheeses.size() * 1.00) + (sauces.size() * 0.50);
        return basePrice + toppingPrice;
    }

    public String details() {
        return "Size: " + size + " inches\n" +
                "Bread: " + breadType + "\n" +
                "Toasted: " + (toasted ? "Yes" : "No") + "\n" +
                "Meats: " + (meats.isEmpty() ? "None" : formatToppings(meats)) + "\n" +
                "Cheeses: " + (cheeses.isEmpty() ? "None" : formatToppings(cheeses)) + "\n" +
                "Sauces: " + (sauces.isEmpty() ? "None" : formatToppings(sauces)) + "\n" +
                "Total: $" + String.format("%.2f", calculateTotal());
    }

    private String formatToppings(List<Topping> toppings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toppings.size(); i++) {
            sb.append(toppings.get(i).getName());
            if (i < toppings.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }
}