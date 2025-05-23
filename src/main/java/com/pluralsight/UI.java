package com.pluralsight;

import java.util.Scanner;

public class UI {

    private static int showHomeScreen(Scanner scanner) {
        System.out.println("\n Welcome to Dean's Deli!!");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Enter option: ");
        return readInt(scanner);
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = showHomeScreen(scanner);
        System.out.println("Selected option: " + option);
        scanner.close();
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
}