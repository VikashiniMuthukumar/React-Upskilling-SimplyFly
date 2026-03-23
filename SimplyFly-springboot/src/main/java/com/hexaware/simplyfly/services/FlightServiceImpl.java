package com.hexaware.simplyfly.services;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Service implementation for CRUD operations on Flight entities.
 * Author: Vikashini
 * Version: 1.0
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.AddFlightDTO;
import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.entities.FlightOwner;
import com.hexaware.simplyfly.entities.Route;
import com.hexaware.simplyfly.exceptions.FlightNotFoundException;
import com.hexaware.simplyfly.exceptions.FlightOwnerNotFoundException;
import com.hexaware.simplyfly.repositories.FlightOwnerRepository;
import com.hexaware.simplyfly.repositories.FlightRepository;
import com.hexaware.simplyfly.repositories.RouteRepository;

@Service
public class FlightServiceImpl implements IFlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightOwnerRepository flightOwnerRepository;
    
    @Autowired
    private RouteRepository routeRepository;

//    @Override
//    public Flight createFlight(AddFlightDTO dto) throws FlightOwnerNotFoundException {
//        Flight flight = new Flight();
//        flight.setName(dto.getName());
//        flight.setFlightCode(dto.getFlightCode());
//        flight.setTotalSeats(dto.getTotalSeats());
//        flight.setCabinBaggageLimit(dto.getCabinBaggageLimit());
//        flight.setCheckInBaggageLimit(dto.getCheckInBaggageLimit());
//
//     // get owner
//        FlightOwner owner = flightOwnerRepository
//                .findById(dto.getOwnerId())
//                .orElseThrow(() ->
//                    new FlightOwnerNotFoundException("Owner not found"));
//
//        // attach owner
//        flight.setOwner(owner);
//        
//        return flightRepository.save(flight);
//    }

    @Override
    public Flight createFlight(AddFlightDTO dto) throws FlightOwnerNotFoundException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        FlightOwner owner = flightOwnerRepository
                .findByUserUsername(username)
                .orElseThrow(() -> new FlightOwnerNotFoundException("Owner not found"));

        Flight flight = new Flight();
        flight.setName(dto.getName());
        flight.setFlightCode(dto.getFlightCode());
        flight.setTotalSeats(dto.getTotalSeats());
        flight.setCabinBaggageLimit(dto.getCabinBaggageLimit());
        flight.setCheckInBaggageLimit(dto.getCheckInBaggageLimit());

        flight.setOwner(owner);

        return flightRepository.save(flight);
    }
    
    @Override
    public Flight updateFlight(Long flightId, FlightDTO dto) throws FlightNotFoundException, FlightOwnerNotFoundException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + flightId));

        flight.setName(dto.getName());
        flight.setFlightCode(dto.getFlightCode());
        flight.setTotalSeats(dto.getTotalSeats());
        flight.setCabinBaggageLimit(dto.getCabinBaggageLimit());
        flight.setCheckInBaggageLimit(dto.getCheckInBaggageLimit());

        return flightRepository.save(flight);
    }

    @Override
    public Flight getFlightByCode(String flightCode) throws FlightNotFoundException {
        System.out.println("Searching for flight code: " + flightCode);
        return flightRepository.findByFlightCode(flightCode)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with code: " + flightCode));
    }


    @Override
    public boolean deleteFlight(Long flightId) throws FlightNotFoundException {
        if (!flightRepository.existsById(flightId)) {
            throw new FlightNotFoundException("Flight not found with ID: " + flightId);
        }
        flightRepository.deleteById(flightId);
        return true;
    }

    @Override
    public Flight getFlightById(Long flightId) throws FlightNotFoundException {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + flightId));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    
    @Override
    public List<String> getAllFlightCodes() {
        return flightRepository.findAll()
                               .stream()
                               .map(Flight::getFlightCode)
                               .toList();
    }
    
    @Override
    public Flight getFlightByCodeAndRoute(String code, String origin, String destination) {
        return flightRepository.findByFlightCodeAndRoute_OriginAndRoute_Destination(code, origin, destination);
    }

//    @Override
//	public List<FlightDTO> findFlightsByDate(LocalDate date) {
//
//	    if (date == null) return Collections.emptyList();
//
//	    List<Flight> flights = flightRepository.findFlightsByRouteDate(date);
//
//	    List<FlightDTO> dtoList = new ArrayList<>();
//
//	    for (Flight f : flights) {
//
//	        if (f.getRoutes() == null) continue;
//
//	        for (Route r : f.getRoutes()) {
//
//	            if (r.getScheduleDate().equals(date)) {
//
//	                FlightDTO dto = new FlightDTO();
//
//	                dto.setFlightId(f.getFlightId());
//	                dto.setName(f.getName());
//	                dto.setFlightCode(f.getFlightCode());
//	                dto.setTotalSeats(f.getTotalSeats());
//	                dto.setCabinBaggageLimit(f.getCabinBaggageLimit());
//	                dto.setCheckInBaggageLimit(f.getCheckInBaggageLimit());
//
//	                dto.setOrigin(r.getOrigin());
//	                dto.setDestination(r.getDestination());
//	                dto.setBaseFare(r.getBaseFare());
//	                dto.setDepartureTime(r.getDepartureTime());
//	                dto.setArrivalTime(r.getArrivalTime());
//
//	                dtoList.add(dto);
//	            }
//	        }
//	    }
//
//	    return dtoList;
//	}
//    
    
    
//    @Override
//    public List<FlightDTO> findFlightsByDate(LocalDate date) {
//
//        List<Route> routes = routeRepository.findByScheduleDate(date);
//
//        List<FlightDTO> list = new ArrayList<>();
//
//        for (Route r : routes) {
//
//            Flight f = r.getFlight();
//
//            if (f == null) {
//                continue;
//            }
//
//            FlightDTO dto = new FlightDTO();
//
//            dto.setFlightId(f.getFlightId());
//            dto.setName(f.getName());
//            dto.setFlightCode(f.getFlightCode());
//            dto.setTotalSeats(f.getTotalSeats());
//            dto.setCabinBaggageLimit(f.getCabinBaggageLimit());
//            dto.setCheckInBaggageLimit(f.getCheckInBaggageLimit());
//
//            dto.setOrigin(r.getOrigin());
//            dto.setDestination(r.getDestination());
//            dto.setBaseFare(r.getBaseFare());
//            dto.setDepartureTime(r.getDepartureTime());
//            dto.setArrivalTime(r.getArrivalTime());
//
//            list.add(dto);
//        }
//
//        return list;
//    }
    
    
    @Override
    public List<FlightDTO> findFlightsByDate(LocalDate date) {

        List<Route> routes = routeRepository.findAll();

        List<FlightDTO> list = new ArrayList<>();

        for (Route r : routes) {

            if (r.getScheduleDate() == null) {
                continue;
            }

            if (!r.getScheduleDate().equals(date)) {
                continue;
            }

            Flight f = r.getFlight();

            if (f == null) {
                continue;
            }

            FlightDTO dto = new FlightDTO();

            dto.setFlightId(f.getFlightId());
            dto.setName(f.getName());
            dto.setFlightCode(f.getFlightCode());
            dto.setTotalSeats(f.getTotalSeats());
            dto.setCabinBaggageLimit(f.getCabinBaggageLimit());
            dto.setCheckInBaggageLimit(f.getCheckInBaggageLimit());

            dto.setOrigin(r.getOrigin());
            dto.setDestination(r.getDestination());
            dto.setBaseFare(r.getBaseFare()); // convert Double → Integer
            dto.setDepartureTime(r.getDepartureTime());
            dto.setArrivalTime(r.getArrivalTime());

            list.add(dto);
        }

        return list;
    }
   
    @Override
    public List<Flight> getFlightsByOwner(String username) {
        return flightRepository.findByOwnerUserUsername(username);
    }
    
    @Override
    public List<Flight> getFlightsByOwner(Long ownerId) {
    	return flightRepository.findFlightsByOwnerId(ownerId);
    }
    
    

}