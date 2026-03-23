package com.hexaware.simplyfly.exceptions;

/**
 * Custom exception thrown when a Booking entity is not found in the system.
 * 
 * This exception extends the generic Exception class.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

public class BookingNotFoundException extends Exception {
    public BookingNotFoundException(String message) {
        super(message);
    }
}

