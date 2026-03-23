package com.hexaware.simplyfly.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the Flight entity.
 * Provides built-in CRUD methods and custom update operations.
 *
 * Author: Vikashini
 * Version: 1.0
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.entities.FlightOwner;
import com.hexaware.simplyfly.entities.UserLogin;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Modifying
	@Query("UPDATE Flight f SET f.cabinBaggageLimit = :limit WHERE f.flightId = :flightId")
	int updateCabinBaggageLimit(@Param("limit") int limit, @Param("flightId") Long flightId);
	
	@Query("SELECT f FROM Flight f WHERE f.flightCode = :flightCode")
	Optional<Flight> findByFlightCode(@Param("flightCode") String string);
	
	@Query("SELECT f.flightCode FROM Flight f")
	List<String> findAllFlightCodes();
	
	Flight findByFlightCodeAndRoute_OriginAndRoute_Destination(String flightCode, String origin, String destination);
	List<Flight> findByOwner(FlightOwner  owner);
	
	@Query("SELECT DISTINCT f FROM Flight f JOIN f.routes r WHERE r.scheduleDate = :date")
    List<Flight> findFlightsByRouteDate(@Param("date") LocalDate date);
	
//	@Query("SELECT f FROM Flight f WHERE f.owner.owner_id = :ownerId")
//	List<Flight> findFlightsByOwnerId(@Param("ownerId") Long ownerId);
	
	 @Query("SELECT f FROM Flight f WHERE f.owner.ownerId = :ownerId")
	 List<Flight> getFlightsByOwnerId(@Param("ownerId") Long ownerId);

	 List<Flight> findByOwnerUserUsername(String username);

	 @Query("SELECT f FROM Flight f WHERE f.owner.ownerId = :ownerId")
	 List<Flight> findFlightsByOwnerId(@Param("ownerId") Long ownerId);
	
//	Flight findByFlightCode(String flightCode);
}
