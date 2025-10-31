package com.example.customexceptions;

public class CustomAgeException extends Exception {
    public CustomAgeException(String message) {
        super(message);
    }
}