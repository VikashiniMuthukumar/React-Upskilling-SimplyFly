package com.hexaware.simplyfly.exceptions;

/**
 * Custom exception thrown when a FlightOwner entity is not found in the system.
 * 
 * This exception is typically used when trying to retrieve or access a flight owner
 * that does not exist in the database.
 * 
 * Author: Vikashini  
 * Version: 1.0
 */

public class FlightOwnerNotFoundException extends Exception {
    public FlightOwnerNotFoundException(String message) {
        super(message);
    }
}

