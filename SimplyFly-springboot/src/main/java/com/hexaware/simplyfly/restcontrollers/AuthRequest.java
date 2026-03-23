package com.hexaware.simplyfly.restcontrollers;

/**
 * DTO for user login request containing username and password.
 * 
 * Author: Vikashini
 * Version: 1.0
 */
import jakarta.validation.constraints.NotBlank;

public class AuthRequest{
        @NotBlank(message = "Username is required")
        String username;
        @NotBlank(message = "Password is required")
        String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
        
        
}
