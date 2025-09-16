package com.example;

public class Palindrome {

    public static String reverseString(String text) {
        String reversed = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed = reversed + text.charAt(i);
        }
        return reversed;
    }

    public static boolean isPalindrome(String word) {
        String original = word.toLowerCase();
        String reversed = reverseString(original);

        return original.equals(reversed);
    }
}