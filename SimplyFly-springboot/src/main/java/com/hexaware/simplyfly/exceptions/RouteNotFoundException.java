package com.hexaware.simplyfly.exceptions;

/**
 * Custom exception thrown when a requested Route is not found in the system.
 * 
 * This is used in service and controller layers to indicate that
 * the route with the given ID or parameters does not exist.
 * 
 * Author: Vikashini  
 * Version: 1.0
 */

public class RouteNotFoundException extends Exception {
    public RouteNotFoundException(String message) {
        super(message);
    }
}

