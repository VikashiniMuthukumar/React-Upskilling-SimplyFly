package com.hexaware.simplyfly.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.JwtResponse;
import com.hexaware.simplyfly.dto.LoginRequest;
import com.hexaware.simplyfly.dto.RegisterRequest;

/**
 * REST controller for user authentication and registration.
 * Handles JWT token creation and user registration.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.repositories.UserRepository;
import com.hexaware.simplyfly.security.JwtUtil;
import com.hexaware.simplyfly.security.UserDetailsImpl;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

    @Autowired 
    private UserRepository userRepo;
    @Autowired 
    private PasswordEncoder encoder;
    @Autowired 
    private JwtUtil jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setRoles(req.getRoles());
        
        userRepo.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getRoles()));
        } catch (Exception e) {
            e.printStackTrace(); // 
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Login failed: " + e.getMessage());
        }
    }

}
