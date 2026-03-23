package com.hexaware.simplyfly.restcontrollers;

/**
 * REST controller for Admin, User, and Flight management.
 * Handles CRUD operations via IAdminService.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.AdminDTO;
import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.Admin;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.AdminNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.services.IAdminService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admins")
public class AdminRestController {

    @Autowired
    private IAdminService adminService;

    // Admin endpoints

    @PostMapping
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody AdminDTO adminDTO) throws UserNotFoundException {
        log.info("Registering new admin: {}", adminDTO);
        adminService.registerAdmin(adminDTO);
        return new ResponseEntity<>("Admin successfully registered.", HttpStatus.CREATED);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<String> updateAdmin(@PathVariable Long adminId, @Valid @RequestBody AdminDTO adminDTO)
            throws AdminNotFoundException, UserNotFoundException {
        log.info("Updating admin with ID: {}", adminId);
        adminService.updateAdmin(adminId, adminDTO);
        return ResponseEntity.ok("Admin updated successfully.");
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long adminId) throws AdminNotFoundException {
        log.warn("Deleting admin with ID: {}", adminId);
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok("Admin deleted successfully.");
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long adminId) throws AdminNotFoundException {
        log.info("Fetching admin with ID: {}", adminId);
        Admin admin = adminService.getAdminById(adminId);
        return ResponseEntity.ok(admin);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        log.info("Fetching all admins");
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    // User endpoints

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("Adding new user: {}", userDTO);
        adminService.addUser(userDTO);
        return new ResponseEntity<>("User successfully added.", HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @Valid @RequestBody UserDTO userDTO)
            throws UserNotFoundException {
        log.info("Updating user with ID: {}", userId);
        adminService.updateUser(userId, userDTO);
        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        log.warn("Deleting user with ID: {}", userId);
        adminService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws UserNotFoundException {
        log.info("Fetching user with ID: {}", userId);
        User user = adminService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Fetching all users");
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    // Flight endpoints

    @PostMapping("/flights")
    public ResponseEntity<String> addFlight(@Valid @RequestBody FlightDTO flightDTO) {
        log.info("Adding new flight: {}", flightDTO);
        adminService.addFlight(flightDTO);
        return new ResponseEntity<>("Flight successfully added.", HttpStatus.CREATED);
    }

    @PutMapping("/flights/{flightId}")
    public ResponseEntity<String> updateFlight(@PathVariable Long flightId, @Valid @RequestBody FlightDTO flightDTO) {
        log.info("Updating flight with ID: {}", flightId);
        adminService.updateFlight(flightId, flightDTO);
        return ResponseEntity.ok("Flight updated successfully.");
    }

    @DeleteMapping("/flights/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) {
        log.warn("Deleting flight with ID: {}", flightId);
        adminService.deleteFlight(flightId);
        return ResponseEntity.ok("Flight deleted successfully.");
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        log.info("Fetching all flights");
        return ResponseEntity.ok(adminService.getAllFlights());
    }

    @GetMapping("/flights/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long flightId) {
        log.info("Fetching flight with ID: {}", flightId);
        Flight flight = adminService.getFlightById(flightId);
        return ResponseEntity.ok(flight);
    }

}