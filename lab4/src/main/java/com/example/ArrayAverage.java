package com.example;



public class ArrayAverage {
public static void main(String[ ] args) {
        int[ ] arr = {1, 2, 3, 4, 5};
        int sum = 0;
        try {
            
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }
            double average = (double) sum / arr.length;
            System.out.println("Среднее значение: " + average);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}