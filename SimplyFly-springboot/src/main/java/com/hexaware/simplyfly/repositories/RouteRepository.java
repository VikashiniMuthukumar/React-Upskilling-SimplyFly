package com.hexaware.simplyfly.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the Route entity.
 * Extends JpaRepository to provide CRUD operations on Route.
 *
 * Author: Vikashini
 * Version: 1.0
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

	List<Route> findByFlight_FlightCode(String flightCode);

	Optional<Route> findByOriginAndDestination(String origin, String destination);
	
	Optional<Route> findByOriginAndDestinationAndFlight_FlightCode(String origin, String destination, String flightCode);

	List<Route> findByScheduleDate(LocalDate scheduleDate);
	
	//List<Route> findByFlight_Owner_Owner_id(Long owner_id);
	
	List<Route> findByFlightOwnerOwnerId(Long ownerId);

}
