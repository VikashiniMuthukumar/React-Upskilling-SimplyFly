package com.hexaware.simplyfly.exceptions;

/**
 * Custom exception thrown when a Flight entity is not found in the system.
 * 
 * This exception is used to indicate that a flight with the given identifier does not exist.
 * 
 * Author: Vikashini  
 * Version: 1.0
 */

public class FlightNotFoundException extends Exception {
    public FlightNotFoundException(String message) {
        super(message);
    }
}

