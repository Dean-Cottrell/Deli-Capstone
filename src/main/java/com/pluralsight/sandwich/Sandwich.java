package com.pluralsight.sandwich;

import com.pluralsight.topping.Meat;
import com.pluralsight.topping.Cheese;
import com.pluralsight.topping.Sauce;
import com.pluralsight.topping.Topping;

import java.util.List;

public class Sandwich {
    private String size;
    private String breadType;
    private List<Meat> meats;
    private List<Cheese> cheeses;
    private List<Sauce> sauces;
    private boolean extraCheese;
    private boolean extraMeat;
    private boolean toasted;

    public Sandwich(String size, String breadType, List<Meat> meats, List<Cheese> cheeses, List<Sauce> sauces, boolean extraCheese, boolean extraMeat) {
        this.size = size;
        this.breadType = breadType;
        this.meats = meats;
        this.cheeses = cheeses;
        this.sauces = sauces;
        this.extraCheese = extraCheese;
        this.extraMeat = extraMeat;
        this.toasted = toasted;
    }

    public Sandwich(String size, String breadType, List<Topping> toppings) {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public List<Meat> getMeats() {
        return meats;
    }

    public void setMeats(List<Meat> meats) {
        this.meats = meats;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public List<Sauce> getSauces() {
        return sauces;
    }

    public void setSauces(List<Sauce> sauces) {
        this.sauces = sauces;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public double calculatePrice() {
        double total = 0;
        for (Meat meat : meats) total += meat.getPrice(size);
        for (Cheese cheese : cheeses) total += cheese.getPrice(size);
        for (Sauce sauce : sauces) total += sauce.getPrice(size);
        if (extraCheese) total += 1.00;
        if (extraMeat) total += 1.50;
        return total;
    }

    public String details() {
        return size + " inch " + breadType + " sandwich with " + meats.size() + " meats, " +
                cheeses.size() + " cheeses, " + sauces.size() + " sauces - $" + calculatePrice();
    }
}