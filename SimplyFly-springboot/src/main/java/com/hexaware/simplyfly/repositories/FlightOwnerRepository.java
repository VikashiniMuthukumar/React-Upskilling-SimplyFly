package com.hexaware.simplyfly.repositories;

import java.util.Optional;

/**
 * Repository interface for the FlightOwner entity.
 * Inherits standard CRUD operations from JpaRepository.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.FlightOwner;
import com.hexaware.simplyfly.entities.User;

@Repository
public interface FlightOwnerRepository extends JpaRepository<FlightOwner, Long>{
	Optional<FlightOwner> findByUser(User user);
	Optional<FlightOwner> findByUserUsername(String username);
}
