package com.example.customexceptions;

public class CustomUnsupportedOperationException extends Exception {
    public CustomUnsupportedOperationException(String message) {
        super(message);
    }
}