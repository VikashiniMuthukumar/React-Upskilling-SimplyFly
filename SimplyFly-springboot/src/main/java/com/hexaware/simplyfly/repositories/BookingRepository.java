package com.hexaware.simplyfly.repositories;

import java.util.List;

/**
 * Repository interface for Booking entity.
 * Provides built-in CRUD and query support through JpaRepository.
 * 
 * Author: Vikashini
 * Version: 1.0
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Booking;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.enums.BookingStatus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByStatus(BookingStatus status);
	// List<Booking> findByUser(UserLogin user);
	 List<Booking> findByUser(User user);
	List<Booking> findByUsername(String username);
	List<Booking> findByUsernameAndStatus(String username, BookingStatus status);
	//List<Booking> findByUsernameAndStatusNew(String username, BookingStatus status);
	List<Booking> findByUserUsername(String username);
	
}
