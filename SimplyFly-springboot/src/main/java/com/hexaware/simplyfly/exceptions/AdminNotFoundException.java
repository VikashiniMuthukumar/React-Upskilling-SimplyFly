package com.hexaware.simplyfly.exceptions;

/**
 * Custom exception thrown when an Admin entity is not found in the system.
 * 
 * This exception extends the generic Exception class.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

public class AdminNotFoundException extends Exception {
    public AdminNotFoundException(String message) {
        super(message);
    }
}
