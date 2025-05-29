package com.pluralsight;

import java.io.File;
import com.pluralsight.order.Order;
import com.pluralsight.order.OrderFileManager;
import com.pluralsight.sandwich.Sandwich;
import com.pluralsight.addons.Drink;
import com.pluralsight.addons.Chips;
import com.pluralsight.topping.ExtraCheese;
import com.pluralsight.topping.ExtraMeat;
import com.pluralsight.topping.RegularTopping;
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
        System.out.println("\n Welcome to Dean's Deli!! ");
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
        System.out.println("\n Order Screen ");
        System.out.println("1: Add Sandwich");
        System.out.println("2: Add Drink");
        System.out.println("3: Add Chips");
        System.out.println("4: Checkout");
        System.out.println("0: Cancel Order");
        System.out.print("Enter option: ");
        return readInt(scanner);
    }

    private static void addSandwichScreen(Scanner scanner, Order order) {
        int sizeChoice = 0;
        while (sizeChoice < 1 || sizeChoice > 3) {
            System.out.println("Select Sandwich Size:");
            System.out.println("1: 4 inches");
            System.out.println("2: 8 inches");
            System.out.println("3: 12 inches");
            System.out.print("Enter option: ");
            sizeChoice = readInt(scanner);
        }
        String size = (sizeChoice == 1) ? "4" : (sizeChoice == 2) ? "8" : "12";

        int breadChoice = 0;
        while (breadChoice < 1 || breadChoice > 4) {
            System.out.println("Select your bread:");
            System.out.println("1: White");
            System.out.println("2: Wheat");
            System.out.println("3: Rye");
            System.out.println("4: Wrap");
            System.out.print("Enter option: ");
            breadChoice = readInt(scanner);
        }
        String breadType = switch (breadChoice) {
            case 1 -> "White";
            case 2 -> "Wheat";
            case 3 -> "Rye";
            case 4 -> "Wrap";
            default -> "";
        };

        List<Topping> toppings = new ArrayList<>();

        String[] meatOptions = {"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"};
        System.out.println("Select Meat: Choose one");
        printOptions(meatOptions);
        System.out.print("Enter option: ");
        int meatChoice = readInt(scanner);
        toppings.add(new ExtraMeat(meatOptions[meatChoice - 1]));

        System.out.println("Would you like extra meat? 1: Yes  2: No");
        int extraMeatChoice = readInt(scanner);
        if (extraMeatChoice == 1) {
            toppings.add(new ExtraMeat(meatOptions[meatChoice - 1]));
        }

        String[] cheeseOptions = {"American", "Provolone", "Cheddar", "Swiss"};
        System.out.println("Select Cheese: Choose one");
        printOptions(cheeseOptions);
        System.out.print("Enter option: ");
        int cheeseChoice = readInt(scanner);
        toppings.add(new ExtraCheese(cheeseOptions[cheeseChoice - 1]));

        System.out.println("Would you like extra cheese? 1: Yes  2: No");
        int extraCheeseChoice = readInt(scanner);
        if (extraCheeseChoice == 1) {
            toppings.add(new ExtraCheese(cheeseOptions[cheeseChoice - 1]));
        }

        String[] sauceOptions = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};
        System.out.println("Select Sauce: Choose one");
        printOptions(sauceOptions);
        System.out.print("Enter option: ");
        int sauceChoice = readInt(scanner);
        toppings.add(new RegularTopping(sauceOptions[sauceChoice - 1]));

        int toastChoice = 0;
        while (toastChoice != 1 && toastChoice != 2) {
            System.out.println("Would you like your sandwich toasted? 1: Yes  2: No");
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
        String[] drinkOptions = {"Small", "Medium", "Large"};
        System.out.println("Select Drink Size:");
        printOptions(drinkOptions);
        int sizeChoice = readInt(scanner);
        String drinkSize = drinkOptions[sizeChoice - 1];

        String[] flavorOptions = {"Sprite", "Water", "HI-C Orange"};
        System.out.println("Select Drink Flavor:");
        printOptions(flavorOptions);
        int flavorChoice = readInt(scanner);
        String flavor = flavorOptions[flavorChoice - 1];

        order.addDrink(new Drink(drinkSize, flavor));
        System.out.println("Drink added to your order.");
    }

    private static void addChipsScreen(Scanner scanner, Order order) {
        String[] chipsOptions = {"Fritos", "Lay's", "Pringles"};
        System.out.println("Select Chips:");
        printOptions(chipsOptions);
        int chipsChoice = readInt(scanner);
        order.addChips(new Chips(chipsOptions[chipsChoice - 1]));
        System.out.println("Chips added to your order.");
    }

    private static boolean checkoutScreen(Scanner scanner, Order order) {
        System.out.println("\n Checkout: ");
        System.out.println(order.details());

        int confirmChoice = 0;
        while (confirmChoice != 1 && confirmChoice != 2) {
            System.out.println("Confirm order? (1: Confirm, 2: Cancel)");
            confirmChoice = readInt(scanner);
        }

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
    }