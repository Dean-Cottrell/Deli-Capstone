package com.pluralsight;

import com.pluralsight.addons.Chips;
import com.pluralsight.addons.Drink;
import com.pluralsight.order.Order;
import com.pluralsight.order.OrderFileManager;
import com.pluralsight.sandwich.Sandwich;
import com.pluralsight.topping.Topping;
import com.pluralsight.topping.ExtraMeat;
import com.pluralsight.topping.ExtraCheese;
import com.pluralsight.topping.RegularTopping;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

public class UI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int homeChoice = showHomeScreen(scanner);
            if (homeChoice == 0) {
                System.out.println("Exiting application. Goodbye!");
                break;
            } else if (homeChoice == 1) {
                processOrder(scanner);
            }
        }
        scanner.close();
    }

    private static int showHomeScreen(Scanner scanner) {
        System.out.println("\nWelcome to Dean's Deli!");
        System.out.println("1: New Order");
        System.out.println("0: Exit");
        System.out.print("Enter option: ");
        return readInt(scanner);
    }

    private static void processOrder(Scanner scanner) {
        Order order = new Order();
        boolean orderActive = true;
        while (orderActive) {
            int orderOpt = showOrderScreen(scanner);
            switch (orderOpt) {
                case 1 -> addSandwichScreen(scanner, order);
                case 2 -> addDrinkScreen(scanner, order);
                case 3 -> addChipsScreen(scanner, order);
                case 4 -> orderActive = !checkoutScreen(scanner, order);
                case 0 -> {
                    System.out.println("Order canceled. Returning to Home Screen.");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int showOrderScreen(Scanner scanner) {
        System.out.println("\nOrder Screen:");
        System.out.println("1: Add Sandwich");
        System.out.println("2: Add Drink");
        System.out.println("3: Add Chips");
        System.out.println("4: Checkout");
        System.out.println("0: Cancel Order");
        System.out.print("Enter option: ");
        return readInt(scanner);
    }

    private static void addSandwichScreen(Scanner scanner, Order order) {
        String size = selectSize(scanner);
        String breadType = selectBread(scanner);

        List<Topping> toppings = new ArrayList<>();

        selectMeat(scanner, toppings);
        selectCheese(scanner, toppings);
        selectOtherToppings(scanner, toppings);
        selectSauce(scanner, toppings);

        boolean toasted = askToasted(scanner);

        Sandwich sandwich = new Sandwich(size, breadType, toppings);
        sandwich.setToasted(toasted);
        order.addSandwich(sandwich);

        System.out.println("Sandwich added to your order.");
    }

    private static String selectSize(Scanner scanner) {
        System.out.println("Select Sandwich Size:");
        System.out.println("1: 4 inches");
        System.out.println("2: 8 inches");
        System.out.println("3: 12 inches");
        System.out.print("Enter option: ");
        int sizeChoice = readInt(scanner);
        return switch (sizeChoice) {
            case 1 -> "4";
            case 2 -> "8";
            case 3 -> "12";
            default -> "";
        };
    }

    private static String selectBread(Scanner scanner) {
        System.out.println("Select Bread:");
        System.out.println("1: White");
        System.out.println("2: Wheat");
        System.out.println("3: Rye");
        System.out.println("4: Wrap");
        System.out.print("Enter option: ");
        int breadChoice = readInt(scanner);
        return switch (breadChoice) {
            case 1 -> "White";
            case 2 -> "Wheat";
            case 3 -> "Rye";
            case 4 -> "Wrap";
            default -> "";
        };
    }

    private static void selectMeat(Scanner scanner, List<Topping> toppings) {
        String[] meatOptions = {"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"};
        System.out.println("Select Meat:");
        printOptions(meatOptions);
        System.out.print("Enter option: ");
        int meatChoice = readInt(scanner);
        toppings.add(new ExtraMeat(meatOptions[meatChoice - 1]));

        System.out.println("Would you like extra meat? 1: Yes  2: No");
        if (readInt(scanner) == 1) {
            toppings.add(new ExtraMeat(meatOptions[meatChoice - 1]));
        }
    }

    private static void selectCheese(Scanner scanner, List<Topping> toppings) {
        String[] cheeseOptions = {"American", "Provolone", "Cheddar", "Swiss"};
        System.out.println("Select Cheese:");
        printOptions(cheeseOptions);
        System.out.print("Enter option: ");
        int cheeseChoice = readInt(scanner);
        toppings.add(new ExtraCheese(cheeseOptions[cheeseChoice - 1]));

        System.out.println("Would you like extra cheese? 1: Yes  2: No");
        if (readInt(scanner) == 1) {
            toppings.add(new ExtraCheese(cheeseOptions[cheeseChoice - 1]));
        }
    }

    private static void selectOtherToppings(Scanner scanner, List<Topping> toppings) {
        String[] otherOptions = {"Lettuce", "Tomato", "Onions", "Peppers", "Pickles"};
        System.out.println("Select Other Toppings:");
        printOptions(otherOptions);
        System.out.print("Enter option: ");
        toppings.add(new RegularTopping(otherOptions[readInt(scanner) - 1]));
    }

    private static void selectSauce(Scanner scanner, List<Topping> toppings) {
        String[] sauceOptions = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};
        System.out.println("Select Sauce:");
        printOptions(sauceOptions);
        System.out.print("Enter option: ");
        toppings.add(new RegularTopping(sauceOptions[readInt(scanner) - 1]));

        System.out.println("Would you like extra sauce? 1: Yes  2: No");
        if (readInt(scanner) == 1) {
            toppings.add(new RegularTopping(sauceOptions[readInt(scanner) - 1]));
        }
    }

    private static boolean askToasted(Scanner scanner) {
        System.out.println("Would you like your sandwich toasted? 1: Yes  2: No");
        return readInt(scanner) == 1;
    }
    private static void addDrinkScreen(Scanner scanner, Order order) {
        String[] drinkOptions = {"Water", "Soda", "Juice", "Tea"};
        System.out.println("Select Drink:");
        printOptions(drinkOptions);
        System.out.print("Enter option: ");
        int drinkChoice = readInt(scanner);
        String drinkType = drinkOptions[drinkChoice - 1];

        System.out.println("Select Drink Size:");
        System.out.println("1: Small");
        System.out.println("2: Medium");
        System.out.println("3: Large");
        System.out.print("Enter option: ");
        int sizeChoice = readInt(scanner);
        String drinkSize = switch (sizeChoice) {
            case 1 -> "Small";
            case 2 -> "Medium";
            case 3 -> "Large";
            default -> "Small";
        };

        Drink drink = new Drink(drinkType, drinkSize);
        order.addDrink(drink);

        System.out.println("Drink added to your order: " + drinkType + " (" + drinkSize + ").");
    }
    
    private static void addChipsScreen(Scanner scanner, Order order) {
        String[] chipsOptions = {"Classic", "BBQ", "Sour Cream & Onion", "Cheddar"};
        System.out.println("Select Chips:");
        printOptions(chipsOptions);
        System.out.print("Enter option: ");
        int chipsChoice = readInt(scanner);

        Chips chips = new Chips(chipsOptions[chipsChoice - 1]);
        order.addChips(chips);

        System.out.println("Chips added to your order.");
    }
    private static boolean checkoutScreen(Scanner scanner, Order order) {
        System.out.println("\nCheckout - Confirm Order?");
        System.out.println("1: Confirm");
        System.out.println("2: Cancel");
        int confirmChoice = readInt(scanner);
        if (confirmChoice == 1) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String filename = now.format(formatter) + ".txt";
            File receiptDir = new File("receipts");
            if (!receiptDir.exists()) {
                receiptDir.mkdirs();
            }
            OrderFileManager manager = new OrderFileManager("receipts/" + filename);
            manager.generateReceipt(order);
            System.out.println("Order confirmed! Receipt saved as: " + filename);
            return true;
        } else {
            return false;
        }
    }

    private static void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ": " + options[i]);
        }
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }
}