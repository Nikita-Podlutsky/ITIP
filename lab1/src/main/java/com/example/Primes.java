package com.example;

public class Primes {
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        int lit = (int) Math.sqrt(number); // (int) это как int(), а корень ускорят процесс
        for (int i = 2; i <= lit; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}