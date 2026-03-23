package com.hexaware.simplyfly.restcontrollers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.LoginRequest;
import com.hexaware.simplyfly.entities.UserLogin;
import com.hexaware.simplyfly.security.JwtUtil;
import com.hexaware.simplyfly.services.UserLoginService;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"},
allowCredentials = "true")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserLoginRestController {

	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtUtil jwtUtil;
	    
    @Autowired
    private UserLoginService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserLogin user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
        userService.register(user);
        return ResponseEntity.ok("Registered successfully");
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserLogin user) {
//        boolean valid = userService.validateUser(user.getUsername(), user.getPassword());
//        if (valid) return ResponseEntity.ok("Login successful");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }
    
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        // ✅ USE AUTHENTICATION OBJECT
        String token = jwtUtil.generateJwtToken(authentication);

        // OPTIONAL: return role also
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(Map.of(
            "token", token,
            "username", userDetails.getUsername(),
            "role", userDetails.getAuthorities().iterator().next().getAuthority()
        ));
    }
}
