package com.hexaware.simplyfly.restcontrollers;

/**
 * REST controller for managing User entities.
 * Provides endpoints for user registration, update, deletion, and retrieval.
 * Uses IUserService for business logic.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.repositories.UserRepository;
import com.hexaware.simplyfly.services.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"},
allowCredentials = "true")
//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO dto) {
        log.info("Registering new user with data: {}", dto);
        User user = userService.registerUser(dto);
        log.info("User registered with ID: {}", user.getId());
        return new ResponseEntity<>("User registered successfully with ID: " + user.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User userDetails) {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(username);

            if (optionalUser.isPresent()) {
                User existingUser = optionalUser.get();
                existingUser.setUsername(userDetails.getUsername());
                existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
                existingUser.setEmail(userDetails.getEmail());
                // Add other fields if needed

                userRepository.save(existingUser);
                return ResponseEntity.ok("User updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace(); // ✅ This will show the real error in the console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed: " + e.getMessage());
        }
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        log.warn("Deleting user with ID: {}", userId);
        userService.deleteUser(userId);
        log.info("User deleted with ID: {}", userId);
        return ResponseEntity.ok("User deleted successfully for ID: " + userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws UserNotFoundException {
        log.info("Fetching user with ID: {}", userId);
        User user = userService.getUserById(userId);
        log.info("User fetched: {}", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userService.getAllUsers();
        log.info("Total users fetched: {}", users.size());
        return ResponseEntity.ok(users);
    }
}