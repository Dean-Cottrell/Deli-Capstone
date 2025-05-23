package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class OrderFileManager {
    private String fileName;
    public void generateReceipt(Order order) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(order.details());
            System.out.println("Receipt saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }

    public void loadReceipt() {
        try {
            Files.lines(Path.of(fileName)).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error loading receipt: " + e.getMessage());
        }
    }
}