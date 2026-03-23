package com.hexaware.simplyfly.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hexaware.simplyfly.entities.UserLogin;
import com.hexaware.simplyfly.repositories.UserLoginRepository;

@Service
public class UserLoginService {

    @Autowired
    private UserLoginRepository userRepo;

    public boolean validateUser(String username, String password) {
        Optional<UserLogin> userOpt = userRepo.findByUsername(username.trim());
        return userOpt.isPresent() && userOpt.get().getPassword().equals(password.trim());
    }

    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username.trim());
    }

    public void register(UserLogin user) {
        user.setUsername(user.getUsername().trim());
        user.setPassword(user.getPassword().trim());
        userRepo.save(user);
    }
}
