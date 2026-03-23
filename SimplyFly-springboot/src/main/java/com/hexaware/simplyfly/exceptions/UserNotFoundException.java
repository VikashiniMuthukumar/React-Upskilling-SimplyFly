package com.hexaware.simplyfly.exceptions;

/**
 * Exception thrown when a requested User is not found in the system.
 * 
 * This exception is typically used in the service layer when operations
 * such as retrieving, updating, or deleting a user fail due to the user
 * not existing.
 * 
 * Author: Vikashini  
 * Version: 1.0
 */

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
