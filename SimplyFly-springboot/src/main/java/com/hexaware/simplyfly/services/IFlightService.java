package com.hexaware.simplyfly.services;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.simplyfly.dto.AddFlightDTO;
import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.exceptions.FlightNotFoundException;
import com.hexaware.simplyfly.exceptions.FlightOwnerNotFoundException;

import jakarta.validation.Valid;

public interface IFlightService {
    Flight createFlight(@Valid AddFlightDTO dto) throws FlightOwnerNotFoundException;
    Flight updateFlight(Long flight_id, FlightDTO dto) throws FlightNotFoundException, FlightOwnerNotFoundException;
    boolean deleteFlight(Long flight_id) throws FlightNotFoundException;
    Flight getFlightById(Long flight_id) throws FlightNotFoundException;
    List<Flight> getAllFlights();
    public Flight getFlightByCode(String flightCode) throws FlightNotFoundException;
    public List<String> getAllFlightCodes();
    public Flight getFlightByCodeAndRoute(String code, String origin, String destination) ;
	List<FlightDTO> findFlightsByDate(LocalDate date);
	//List<Flight> getFlightsByOwner(Long ownerId);
	List<Flight> getFlightsByOwner(String username);
	List<Flight> getFlightsByOwner(Long ownerId);
	
	
}
