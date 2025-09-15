package com.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("Простые числа от 2 до 100:");
        runPrimeNumberFinder();
        System.out.println("-".repeat(15)); // Типо "string"*15 в python

        
        System.out.println("Проверка палиндромов:");
        runPalindromeChecker();
    }

    public static void runPrimeNumberFinder() { // static это чтобы не создавать объект класса
        for (int i = 2; i <= 100; i++) { //Можно прибавить по 2, но тогда надо начинать с 3 и вывести 2 врцучную
            if (Primes.isPrime(i)) {
                System.out.print(i+" ");
                
            }if (i % 10 == 0) {System.out.println("");}
        }
    }

    public static void runPalindromeChecker() {
        List<String> words = List.of("мадам", "racecar", "Никита", "level");
        words.forEach(word -> {
            Palindrome.isPalindrome(word);
            if (Palindrome.isPalindrome(word)) {
                System.out.println(word + " - это палиндром.");
            } else {
                System.out.println(word + " - это не палиндром.");
            }
        });
    }
}