package com.hexaware.simplyfly.services;

/**
 * Service for managing CRUD operations on Route entities.
 * 
 * @author Vikashini
 * @version 1.0
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.RouteDTO;
import com.hexaware.simplyfly.dto.RouteFlightDTO;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.entities.Route;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;
import com.hexaware.simplyfly.repositories.FlightRepository;
import com.hexaware.simplyfly.repositories.RouteRepository;

@Service
public class RouteServiceImpl implements IRouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private FlightRepository flightRepository;    
    
    @Override
    public Route createRoute(RouteDTO dto) {
        Flight flight = flightRepository.findByFlightCode(dto.getFlightCode())
                .orElseThrow(() -> new RuntimeException("Flight not found with code: " + dto.getFlightCode()));

        Route route = new Route();
        route.setOrigin(dto.getOrigin());
        route.setDestination(dto.getDestination());
        route.setDepartureTime(dto.getDepartureTime());
        route.setArrivalTime(dto.getArrivalTime());
        route.setBaseFare(dto.getBaseFare());
        route.setScheduleDate(dto.getScheduleDate()); // set in both create and update
        route.setFlight(flight);

        return routeRepository.save(route);
    }

    
    @Override
    public Route updateRoute(Long routeId, RouteDTO dto) throws RouteNotFoundException {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + routeId));

        Flight flight = flightRepository.findByFlightCode(dto.getFlightCode())
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + dto.getFlightCode()));

        route.setOrigin(dto.getOrigin());
        route.setDestination(dto.getDestination());
        route.setDepartureTime(dto.getDepartureTime());
        route.setArrivalTime(dto.getArrivalTime());
        route.setBaseFare(dto.getBaseFare());
        route.setScheduleDate(dto.getScheduleDate()); // set in both create and update
        route.setFlight(flight);

        return routeRepository.save(route);
    }

    @Override
    public boolean deleteRoute(Long routeId) throws RouteNotFoundException {
        if (!routeRepository.existsById(routeId)) {
            throw new RouteNotFoundException("Route not found with ID: " + routeId);
        }
        routeRepository.deleteById(routeId);
        return true;
    }

    @Override
    public Route getRouteById(Long routeId) throws RouteNotFoundException {
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + routeId));
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
    
    @Override
    public List<Route> getRoutesByFlightCode(String flightCode) {
        return routeRepository.findByFlight_FlightCode(flightCode);
    }
    
    @Override
    public Route getRouteByFlightCode(String flightCode) throws RouteNotFoundException {
        List<Route> routes = routeRepository.findByFlight_FlightCode(flightCode);
        if (routes.isEmpty()) {
            throw new RouteNotFoundException("Route not found for flight code " + flightCode);
        }
        return routes.get(0); 
    }

    public RouteFlightDTO getRouteFlightDetails(String origin, String destination, String flightCode)
            throws RouteNotFoundException {

        Route route = routeRepository.findByOriginAndDestinationAndFlight_FlightCode(origin, destination, flightCode)
            .orElseThrow(() -> new RouteNotFoundException("Route not found for given details"));

        Flight flight = route.getFlight(); // assuming @ManyToOne or @OneToOne

        return new RouteFlightDTO(
            route.getBaseFare(),
            route.getArrivalTime(),
            route.getDepartureTime(),
            flight.getCheckInBaggageLimit(),
            flight.getCabinBaggageLimit()
        );
    }
    
    @Override
    public List<Route> getRoutesByDate(java.time.LocalDate date) {
        return routeRepository.findByScheduleDate(date);
    }
    
    
    @Override
    public List<Route> getRoutesByOwner(Long ownerId) {
    	//return routeRepository.findByFlight_Owner_Owner_id(ownerId);
    	
    	return routeRepository.findByFlightOwnerOwnerId(ownerId);
    }

}