package com.example;

import java.util.Scanner;

public class TriangleValidator {

    public static boolean isTriangle(int a, int b, int c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        String[] parts = line.split(",\\s*");
        
        boolean result = false;
        if (parts.length == 3) {
            try {
                int side1 = Integer.parseInt(parts[0].trim());
                int side2 = Integer.parseInt(parts[1].trim());
                int side3 = Integer.parseInt(parts[2].trim());
                result = isTriangle(side1, side2, side3);
            } catch (NumberFormatException e) {
                result = false;
            }
        }

        System.out.println(result);
    }
}