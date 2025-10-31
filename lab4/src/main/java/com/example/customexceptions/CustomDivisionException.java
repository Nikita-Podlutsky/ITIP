package com.example.customexceptions;

public class CustomDivisionException extends Exception {
    public CustomDivisionException(String message) {
        super(message);
    }
}