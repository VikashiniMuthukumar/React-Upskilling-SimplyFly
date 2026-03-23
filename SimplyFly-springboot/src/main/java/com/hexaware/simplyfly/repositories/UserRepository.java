package com.hexaware.simplyfly.repositories;

/**
 * Repository interface for User entity.
 * Extends JpaRepository to provide CRUD operations.
 * 
 * Includes custom methods for finding and checking users by email and username.
 * 
 * Author: Vikashini
 * Version: 1.0
 */
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    
}
