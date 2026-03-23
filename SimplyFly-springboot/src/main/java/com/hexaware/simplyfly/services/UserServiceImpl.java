package com.hexaware.simplyfly.services;

/**
 * Service for managing User CRUD operations.
 * 
 * @author Vikashini
 * @version 1.0
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User registerUser(UserDTO dto) {
    	System.out.println("Registering user with details: " + dto);
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // ✅ must encode!
        user.setRoles(dto.getRole());

       

        return userRepository.save(user);
    }


    @Override
    public User updateUser(String username, User updatedUser) {
        User existing = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setPassword(updatedUser.getPassword());
        existing.setEmail(updatedUser.getEmail());

      
        return userRepository.save(existing);
    }



    @Override
    public boolean deleteUser(Long userId) throws UserNotFoundException {
    	User user = userRepository.findById(userId)
    		    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

    		userRepository.delete(user);
    		return true;
    }


    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }
}