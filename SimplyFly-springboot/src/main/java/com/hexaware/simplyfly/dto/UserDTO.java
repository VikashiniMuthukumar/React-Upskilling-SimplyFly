package com.hexaware.simplyfly.dto;

import com.hexaware.simplyfly.enums.UserRole;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * UserDTO is a data transfer object for user details with validation.
 * @author Vikashini
 * @version 1.0
 */

public class UserDTO {

    private Long user_id;

    @NotBlank
    @Size(max = 100)
    private String username;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @Size(max = 15)
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number must be numeric")
    private String phoneNumber;

    @NotNull
    private String role;

    private LocalDateTime registeredAt;

    private Long admin_id;
    private Long flightOwner_id;
    private String airlineName;
    private String contactNumber;

    public UserDTO() {
    	super();
    }

	

	
	public UserDTO(Long user_id, @NotBlank @Size(max = 100) String username,
			@NotBlank @Email @Size(max = 100) String email,
			@NotBlank @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters") String password,
			@Size(max = 15) @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number must be numeric") String phoneNumber,
			@NotNull String role, LocalDateTime registeredAt, Long admin_id, Long flightOwner_id, String airlineName,
			String contactNumber) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.registeredAt = registeredAt;
		this.admin_id = admin_id;
		this.flightOwner_id = flightOwner_id;
		this.airlineName = airlineName;
		this.contactNumber = contactNumber;
	}

	
	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(LocalDateTime registeredAt) {
		this.registeredAt = registeredAt;
	}

	public Long getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Long admin_id) {
		this.admin_id = admin_id;
	}

	public Long getFlightOwner_id() {
		return flightOwner_id;
	}

	public void setFlightOwner_id(Long flightOwner_id) {
		this.flightOwner_id = flightOwner_id;
	}

	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", username=" + username + ", email=" + email + ", password="
				+ password + ", phoneNumber=" + phoneNumber + ", role=" + role + ", registeredAt=" + registeredAt
				+ ", admin_id=" + admin_id + ", flightOwner_id=" + flightOwner_id + "]";
	}

	

    
    
}