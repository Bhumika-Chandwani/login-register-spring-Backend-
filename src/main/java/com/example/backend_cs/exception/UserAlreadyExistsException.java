package com.example.backend_cs.exception;

public class UserAlreadyExistsException extends RuntimeException {
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

// EXPLANATION:
// Custom exception class for when someone tries to register
// with a username that already exists in the database
// Extends RuntimeException so we don't need try-catch everywhere