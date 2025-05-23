package com.pluralsight;

import com.pluralsight.order.Order;
import com.pluralsight.order.OrderFileManager;
import com.pluralsight.sandwich.Sandwich;
import com.pluralsight.addons.Drink;
import com.pluralsight.addons.Chips;
import com.pluralsight.topping.Topping;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        System.out.println("\n--- Welcome to Dean's Deli ---");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Enter option: ");
        return readInt(scanner);
    }

    private static void processOrder(Scanner scanner) {
        Order order = new Order();
        boolean orderActive = true;
        while (orderActive) {
            int orderOpt = showOrderScreen(scanner);
            switch (orderOpt) {
                case 1:
                    addSandwichScreen(scanner, order);
                    break;
                case 2:
                    addDrinkScreen(scanner, order);
                    break;
                case 3:
                    addChipsScreen(scanner, order);
                    break;
                case 4:
                    if (checkoutScreen(scanner, order)) {
                        orderActive = false;
                    } else {
                        System.out.println("Order canceled. Returning to Home Screen.");
                        return;
                    }
                    break;
                case 0:
                    System.out.println("Order canceled. Returning to Home Screen.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int showOrderScreen(Scanner scanner) {
        System.out.println("\n--- Order Screen ---");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.print("Enter option: ");
        return readInt(scanner);
    }

    private static void addSandwichScreen(Scanner scanner, Order order) {
        int sizeChoice = 0;
        while (sizeChoice < 1 || sizeChoice > 3) {
            System.out.println("Select Sandwich Size:");
            System.out.println("1) 4 inches");
            System.out.println("2) 8 inches");
            System.out.println("3) 12 inches");
            System.out.print("Enter option: ");
            sizeChoice = readInt(scanner);
        }
        String size = (sizeChoice == 1) ? "4" : (sizeChoice == 2) ? "8" : "12";

        int breadChoice = 0;
        while (breadChoice < 1 || breadChoice > 4) {
            System.out.println("Select your bread:");
            System.out.println("1) White");
            System.out.println("2) Wheat");
            System.out.println("3) Rye");
            System.out.println("4) Wrap");
            System.out.print("Enter option: ");
            breadChoice = readInt(scanner);
        }
        String breadType = "";
        switch (breadChoice) {
            case 1: breadType = "White"; break;
            case 2: breadType = "Wheat"; break;
            case 3: breadType = "Rye"; break;
            case 4: breadType = "Wrap"; break;
        }

        String[] toppingOptions = {
                "Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon",
                "American", "Provolone", "Cheddar", "Swiss",
                "Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms",
                "Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"
        };
        List<Topping> toppings = new ArrayList<>();
        int toppingCount = 0;
        System.out.println("Add Toppings (select up to 5, enter 0 to finish):");
        while (toppingCount < 5) {
            printOptions(toppingOptions);
            System.out.println("0) Done");
            System.out.print("Enter option: ");
            int choice = readInt(scanner);
            if (choice == 0) {
                break;
            } else if (choice < 1 || choice > toppingOptions.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                toppings.add(new Topping(toppingOptions[choice - 1]) {
                    @Override
                    public double getPrice(String size) {
                        return 0;
                    }
                });
                toppingCount++;
            }
        }

        int toastChoice = 0;
        while (toastChoice != 1 && toastChoice != 2) {
            System.out.println("Would you like your sandwich toasted? (1: Yes, 2: No)");
            toastChoice = readInt(scanner);
        }
        boolean toasted = (toastChoice == 1);

        Sandwich sandwich = new Sandwich(size, breadType, toppings);
        sandwich.setToasted(toasted);
        order.addSandwich(sandwich);
        System.out.println("Sandwich added to your order.");
    }

    private static void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i]);
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
    private static void addDrinkScreen(Scanner scanner, Order order) {
        int sizeChoice = 0;
        while (sizeChoice < 1 || sizeChoice > 3) {
            System.out.println("\nSelect Drink Size:");
            System.out.println("1) Small");
            System.out.println("2) Medium");
            System.out.println("3) Large");
            System.out.print("Enter option: ");
            sizeChoice = readInt(scanner);
        }
        String drinkSize = (sizeChoice == 1) ? "Small" : (sizeChoice == 2) ? "Medium" : "Large";

        String[] flavorOptions = {"Sprite", "Water", "HI-C Orange"};
        int flavorChoice = 0;
        while (flavorChoice < 1 || flavorChoice > flavorOptions.length) {
            System.out.println("\nSelect Drink Flavor:");
            printOptions(flavorOptions);
            System.out.print("Enter option: ");
            flavorChoice = readInt(scanner);
        }
        String flavor = flavorOptions[flavorChoice - 1];

        order.addDrink(new Drink(drinkSize, flavor));
        System.out.println("Drink added to your order.");
    }
  
}