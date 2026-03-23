package com.hexaware.simplyfly.services;

import java.util.List;

import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;

public interface IUserService {


    User registerUser(UserDTO dto);


    boolean deleteUser(Long userId) throws UserNotFoundException;

    User getUserById(Long userId) throws UserNotFoundException;

    List<User> getAllUsers();
    User updateUser(String username, User updatedUser);
    User findByUsername(String username) throws UserNotFoundException;
}
