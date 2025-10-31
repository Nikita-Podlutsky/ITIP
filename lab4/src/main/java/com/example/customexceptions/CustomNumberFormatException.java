package com.example.customexceptions;

public class CustomNumberFormatException extends Exception {
    public CustomNumberFormatException(String message) {
        super(message);
    }
}