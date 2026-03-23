package com.hexaware.simplyfly.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.simplyfly.entities.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer>{

	Optional<UserLogin> findByUsername(String username);
    Optional<UserLogin> findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
}
