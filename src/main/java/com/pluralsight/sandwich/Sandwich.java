package com.pluralsight.sandwich;

import com.pluralsight.topping.Topping;
import com.pluralsight.topping.RegularTopping;
import com.pluralsight.topping.ExtraMeat;
import com.pluralsight.topping.ExtraCheese;
import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String size;
    private String breadType;
    private boolean toasted;
    private List<ExtraMeat> meats;
    private List<ExtraCheese> cheeses;
    private List<RegularTopping> regularToppings;

    public Sandwich(String size, String breadType, List<Topping> toppings) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = false;
        meats = new ArrayList<>();
        cheeses = new ArrayList<>();
        regularToppings = new ArrayList<>();

        for (Topping topping : toppings) {
            if (isMeat(topping.getName())) {
                meats.add(new ExtraMeat(topping.getName()));
            } else if (isCheese(topping.getName())) {
                cheeses.add(new ExtraCheese(topping.getName()));
            } else {
                regularToppings.add(new RegularTopping(topping.getName()));
            }
        }
    }

    private boolean isMeat(String name) {
        return List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon").contains(name);
    }

    private boolean isCheese(String name) {
        return List.of("American", "Provolone", "Cheddar", "Swiss").contains(name);
    }

    public double calculateTotal() {
        double basePrice = switch (size) {
            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> 0;
        };
        double toppingPrice = meats.stream().mapToDouble(t -> t.getPrice(size)).sum() +
                cheeses.stream().mapToDouble(t -> t.getPrice(size)).sum() +
                regularToppings.stream().mapToDouble(t -> t.getPrice(size)).sum();
        return basePrice + toppingPrice;
    }

    public String details() {
        return "Size: " + size + " inches\n" +
                "Bread: " + breadType + "\n" +
                "Toasted: " + (toasted ? "Yes" : "No") + "\n" +
                "Meats: " + formatToppings(meats) + "\n" +
                "Cheeses: " + formatToppings(cheeses) + "\n" +
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