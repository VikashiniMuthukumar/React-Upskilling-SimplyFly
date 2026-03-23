package com.hexaware.simplyfly.restcontrollers;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * REST controller for managing Flight entities.
 * Supports CRUD operations for flights.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.AddFlightDTO;
import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.entities.FlightOwner;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.FlightNotFoundException;
import com.hexaware.simplyfly.exceptions.FlightOwnerNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.repositories.FlightOwnerRepository;
import com.hexaware.simplyfly.services.IFlightService;
import com.hexaware.simplyfly.services.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"},
allowCredentials = "true")
//@CrossOrigin("http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/api/flights")
public class FlightRestController {

	@Autowired
	private IFlightService flightService;
	
	@Autowired
	private IUserService userService; // or your UserService interfaceO

	@Autowired
	private FlightOwnerRepository flightOwnerRepository;
	@PostMapping("/add")
	public ResponseEntity<Map<String, String>> createFlight(@Valid @RequestBody AddFlightDTO dto)
			throws FlightOwnerNotFoundException {
		log.info("Creating Flight with data: {}", dto);
		Flight flight = flightService.createFlight(dto);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Flight created successfully");
		response.put("flightId", String.valueOf(flight.getFlightId()));

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{flightId}")
	public ResponseEntity<Map<String, String>> updateFlight(@PathVariable Long flightId,
			@Valid @RequestBody FlightDTO dto) throws FlightNotFoundException, FlightOwnerNotFoundException {

		log.info("Updating Flight with ID: {}", flightId);
		Flight flight = flightService.updateFlight(flightId, dto);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Flight updated successfully");
		response.put("flightId", String.valueOf(flight.getFlightId()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{flightId}")
	public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) throws FlightNotFoundException {
		log.warn("Deleting Flight with ID: {}", flightId);
		flightService.deleteFlight(flightId);
		log.info("Flight deleted with ID: {}", flightId);
		return ResponseEntity.ok("Flight deleted successfully for ID: " + flightId);
	}

	@GetMapping("/{flightId}")
	public ResponseEntity<Flight> getFlightById(@PathVariable Long flightId) throws FlightNotFoundException {
		log.info("Fetching Flight with ID: {}", flightId);
		Flight flight = flightService.getFlightById(flightId);
		log.info("Flight fetched: {}", flight);
		return ResponseEntity.ok(flight);
	}

//	@GetMapping("/all")
//	public ResponseEntity<List<Flight>> getAllFlights() {
//		log.info("Fetching all Flights");
//		List<Flight> flights = flightService.getAllFlights();
//		log.info("Total Flights fetched: {}", flights.size());
//		return ResponseEntity.ok(flights);
//	}
	
//	@GetMapping("/all")
//	public ResponseEntity<List<FlightDTO>> getAllFlights() {
//	    log.info("Fetching all Flights");
//
//	    List<FlightDTO> dtos = flightService.getAllFlights()
//	        .stream()
//	        .map(flight -> {
//	            FlightDTO dto = new FlightDTO();
//	            dto.setFlightId(flight.getFlightId());
//	            dto.setName(flight.getName());
//	            dto.setFlightCode(flight.getFlightCode());
//	            dto.setTotalSeats(flight.getTotalSeats());
//	            dto.setCabinBaggageLimit(flight.getCabinBaggageLimit());
//	            dto.setCheckInBaggageLimit(flight.getCheckInBaggageLimit());
//	            return dto;
//	        })
//	        .toList();
//
//	    return ResponseEntity.ok(dtos);
//	}

//	@GetMapping("/all")
//	public ResponseEntity<List<FlightDTO>> getAllFlights(Authentication auth) throws UserNotFoundException {
//	    log.info("Fetching flights for user: {}", auth.getName());
//
//	    // Fetch the user from DB
//	    User user = userService.findByUsername(auth.getName());
//	    List<Flight> flights;
//
//	    if (user.getRoles().contains("ADMIN")) {
//	        flights = flightService.getAllFlights(); // all flights
//	    } else if (user.getRoles().contains("FLIGHT_OWNER")) {
//	        flights = flightService.getFlightsByOwner(user.getId()); // only owner's flights
//	    } else {
//	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//	    }
//
//	    // Convert to DTOs
//	    List<FlightDTO> dtos = flights.stream().map(flight -> {
//	        FlightDTO dto = new FlightDTO();
//	        dto.setFlightId(flight.getFlightId());
//	        dto.setName(flight.getName());
//	        dto.setFlightCode(flight.getFlightCode());
//	        dto.setTotalSeats(flight.getTotalSeats());
//	        dto.setCabinBaggageLimit(flight.getCabinBaggageLimit());
//	        dto.setCheckInBaggageLimit(flight.getCheckInBaggageLimit());
//	        return dto;
//	    }).toList();
//
//	    return ResponseEntity.ok(dtos);
//	}
//	corrected above

//	@GetMapping("/all")
//	public ResponseEntity<List<FlightDTO>> getAllFlights(Authentication auth) throws UserNotFoundException, FlightOwnerNotFoundException {
//	    log.info("Fetching flights for user: {}", auth.getName());
//
//	    User user = userService.findByUsername(auth.getName());
//	    List<Flight> flights;
//
//	    if (user.getRoles().contains("ADMIN")) {
//	        flights = flightService.getAllFlights();
//	    } else if (user.getRoles().contains("FLIGHT_OWNER")) {
//	        FlightOwner owner = flightOwnerRepository.findByUser(user)
//	            .orElseThrow(() -> new FlightOwnerNotFoundException("No flight owner for user " + user.getUsername()));
//	        flights = flightService.getFlightsByOwner(owner.getOwner_id());
//	    } else {
//	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//	    }
//
//	    List<FlightDTO> dtos = flights.stream().map(flight -> {
//	        FlightDTO dto = new FlightDTO();
//	        dto.setFlightId(flight.getFlightId());
//	        dto.setName(flight.getName());
//	        dto.setFlightCode(flight.getFlightCode());
//	        dto.setTotalSeats(flight.getTotalSeats());
//	        dto.setCabinBaggageLimit(flight.getCabinBaggageLimit());
//	        dto.setCheckInBaggageLimit(flight.getCheckInBaggageLimit());
//	        return dto;
//	    }).toList();
//
//	    return ResponseEntity.ok(dtos);
//	}
	
//	@GetMapping("/all")
//	public ResponseEntity<List<FlightDTO>> getAllFlights() {
//	    List<Flight> flights = flightService.getAllFlights();
//	    
//	    List<FlightDTO> dtos = flights.stream().map(flight -> {
//	        FlightDTO dto = new FlightDTO();
//	        dto.setFlightId(flight.getFlightId());
//	        dto.setName(flight.getName());
//	        dto.setFlightCode(flight.getFlightCode());
//	        dto.setTotalSeats(flight.getTotalSeats());
//	        dto.setCabinBaggageLimit(flight.getCabinBaggageLimit());
//	        dto.setCheckInBaggageLimit(flight.getCheckInBaggageLimit());
//	        return dto;
//	    }).toList();
//
//	    return ResponseEntity.ok(dtos);
//	}
	
//	@GetMapping("/all")
//	public ResponseEntity<List<FlightDTO>> getAllFlights(Authentication auth) 
//	        throws UserNotFoundException, FlightOwnerNotFoundException {
//
//	    List<Flight> flights;
//
//	    if (auth != null) {
//	        // User is logged in, fetch based on role
//	        User user = userService.findByUsername(auth.getName());
//
//	        if (user.getRoles().contains("ADMIN")) {
//	            flights = flightService.getAllFlights(); // all flights
//	        } else if (user.getRoles().contains("FLIGHT_OWNER")) {
//	            FlightOwner owner = flightOwnerRepository.findByUser(user)
//	                    .orElseThrow(() -> new FlightOwnerNotFoundException("No flight owner for user " + user.getUsername()));
//	            flights = flightService.getFlightsByOwner(owner.getOwner_id()); // only owner's flights
//	        } else {
//	            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//	        }
//	    } else {
//	        // No authentication provided, return all flights (for testing / public)
//	        flights = flightService.getAllFlights();
//	    }
//
//	    // Convert to DTOs
//	    List<FlightDTO> dtos = flights.stream().map(flight -> {
//	        FlightDTO dto = new FlightDTO();
//	        dto.setFlightId(flight.getFlightId());
//	        dto.setName(flight.getName());
//	        dto.setFlightCode(flight.getFlightCode());
//	        dto.setTotalSeats(flight.getTotalSeats());
//	        dto.setCabinBaggageLimit(flight.getCabinBaggageLimit());
//	        dto.setCheckInBaggageLimit(flight.getCheckInBaggageLimit());
//	        return dto;
//	    }).toList();
//
//	    return ResponseEntity.ok(dtos);
//	}
	
//	@GetMapping("/all")
//	public ResponseEntity<List<FlightDTO>> getAllFlights(Authentication auth) 
//	        throws UserNotFoundException, FlightOwnerNotFoundException {
//
//	    if (auth == null) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//	    }
//
//	    User user = userService.findByUsername(auth.getName());
//	    List<Flight> flights;
//
//	    if (user.getRoles().contains("ROLE_ADMIN")) {
//	        flights = flightService.getAllFlights();
//	    } else if (user.getRoles().contains("ROLE_FLIGHT_OWNER")) {
//	        FlightOwner owner = flightOwnerRepository.findByUser(user)
//	                .orElseThrow(() -> new FlightOwnerNotFoundException(
//	                    "No flight owner for user " + user.getUsername()));
//	        flights = flightService.getFlightsByOwner(owner.getOwner_id()); // only their flights
//	    } else {
//	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//	    }
//
//	    List<FlightDTO> dtos = flights.stream().map(flight -> {
//	        FlightDTO dto = new FlightDTO();
//	        dto.setFlightId(flight.getFlightId());
//	        dto.setName(flight.getName());
//	        dto.setFlightCode(flight.getFlightCode());
//	        dto.setTotalSeats(flight.getTotalSeats());
//	        dto.setCabinBaggageLimit(flight.getCabinBaggageLimit());
//	        dto.setCheckInBaggageLimit(flight.getCheckInBaggageLimit());
//	        return dto;
//	    }).toList();
//
//	    return ResponseEntity.ok(dtos);
//	}
//	
	@GetMapping("/all")
	public ResponseEntity<List<FlightDTO>> getAllFlights(Authentication auth)
	        throws UserNotFoundException, FlightOwnerNotFoundException {
		System.out.println("Endpoint reached");
	    if (auth == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    User user = userService.findByUsername(auth.getName());
	    List<Flight> flights;

	    if (user.getRoles().contains("ROLE_ADMIN")) {
	        flights = flightService.getAllFlights();
	    } 
	    else if (user.getRoles().contains("ROLE_FLIGHT_OWNER")) {

	        FlightOwner owner = flightOwnerRepository.findByUser(user)
	                .orElseThrow(() -> new FlightOwnerNotFoundException(
	                        "No flight owner for user " + user.getUsername()));

	        flights = flightService.getFlightsByOwner(owner.getOwnerId());
	    } 
	    else {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }

	    List<FlightDTO> dtos = flights.stream().map(flight -> {
	    	FlightDTO dto = new FlightDTO();
	        dto.setFlightId(flight.getFlightId());
	        dto.setName(flight.getName());
	        dto.setFlightCode(flight.getFlightCode());
	        dto.setTotalSeats(flight.getTotalSeats());
	        dto.setCabinBaggageLimit(flight.getCabinBaggageLimit());
	        dto.setCheckInBaggageLimit(flight.getCheckInBaggageLimit());
	        return dto;
	    }).toList();
	    System.out.println("User: " + auth.getName());
	    System.out.println("Authorities: " + auth.getAuthorities());
	    return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/byCode/{flightCode}")
	public ResponseEntity<Flight> getFlightByCode(@PathVariable String flightCode) throws FlightNotFoundException {
		return ResponseEntity.ok(flightService.getFlightByCode(flightCode));
	}

	@GetMapping("/codes")
	public ResponseEntity<List<String>> getAllFlightCodes() {
		List<String> codes = flightService.getAllFlightCodes();
		return ResponseEntity.ok(codes);
	}

//	@GetMapping("/search")
//	public ResponseEntity<Flight> getFlightByCodeAndRoute(@PathVariable String code, @PathVariable String origin,
//			@PathVariable String destination) {
//		Flight flight = flightService.getFlightByCodeAndRoute(code, origin, destination);
//		if (flight == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//		return ResponseEntity.ok(flight);
//	}
	
	@GetMapping("/search/{code}/{origin}/{destination}")
	public ResponseEntity<FlightDTO> getFlightByCodeAndRoute(
	        @PathVariable String code,
	        @PathVariable String origin,
	        @PathVariable String destination) {

	    Flight flight = flightService.getFlightByCodeAndRoute(code, origin, destination);

	    if (flight == null) {
	        return ResponseEntity.notFound().build();
	    }

	    FlightDTO dto = new FlightDTO();
	    dto.setFlightId(flight.getFlightId());
	    dto.setName(flight.getName());
	    dto.setFlightCode(flight.getFlightCode());
	    dto.setTotalSeats(flight.getTotalSeats());
	    dto.setCabinBaggageLimit(flight.getCabinBaggageLimit());
	    dto.setCheckInBaggageLimit(flight.getCheckInBaggageLimit());

	    return ResponseEntity.ok(dto);
	}
	
	
	@GetMapping("/date/{date}")
	public List<FlightDTO> getFlightsByDate(
	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

	    return flightService.findFlightsByDate(date);
	}
	
//	@GetMapping("/owner/{ownerId}")
//	public List<Flight> getFlightsByOwner(@PathVariable Long ownerId) {
//	    return flightService.getFlightsByOwner(ownerId);
//	}
	
	
	@GetMapping("/owner/flights")
	public List<Flight> getFlightsForOwner() throws UserNotFoundException, FlightOwnerNotFoundException {

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findByUsername(auth.getName());

	    FlightOwner owner = flightOwnerRepository.findByUser(user)
	            .orElseThrow(() -> new FlightOwnerNotFoundException("Owner not found"));

	    return flightService.getFlightsByOwner(owner.getOwnerId());
	}

}