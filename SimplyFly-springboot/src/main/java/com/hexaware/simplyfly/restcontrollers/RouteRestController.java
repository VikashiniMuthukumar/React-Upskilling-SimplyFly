package com.hexaware.simplyfly.restcontrollers;

/**
 * REST controller for managing Route entities.
 * Provides endpoints to create, update, delete, and fetch routes.
 * Uses IRouteService for business logic.
 * 
 * Author: Vikashini
 * Version: 1.0
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.RouteDTO;
import com.hexaware.simplyfly.dto.RouteFlightDTO;
import com.hexaware.simplyfly.entities.FlightOwner;
import com.hexaware.simplyfly.entities.Route;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.FlightOwnerNotFoundException;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.repositories.FlightOwnerRepository;
import com.hexaware.simplyfly.services.IRouteService;
import com.hexaware.simplyfly.services.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"},
allowCredentials = "true")
//@CrossOrigin("http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/routes")
public class RouteRestController {

    @Autowired
    private IRouteService routeService;

    @Autowired
    private FlightOwnerRepository flightOwnerRepository;
    
    @Autowired
	private IUserService userService; 
	
    @PostMapping("/add")
    public ResponseEntity<String> createRoute(@Valid @RequestBody RouteDTO dto) {
        System.out.println("Received RouteDTO: " + dto);
        Route route = routeService.createRoute(dto);
        System.out.println("Route saved with ID: " + route.getRoute_id());
        return new ResponseEntity<>("Route created successfully with ID: " + route.getRoute_id(), HttpStatus.CREATED);
    }


    @PutMapping("/update/{routeId}")
    public ResponseEntity<String> updateRoute(@PathVariable Long routeId, @Valid @RequestBody RouteDTO dto) throws RouteNotFoundException {
        log.info("Updating Route with ID: {}", routeId);
        Route route = routeService.updateRoute(routeId, dto);
        log.info("Route updated: {}", route);
        return ResponseEntity.ok("Route updated successfully for ID: " + route.getRoute_id());
    }

    @DeleteMapping("/delete/{routeId}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long routeId) throws RouteNotFoundException {
        log.warn("Deleting Route with ID: {}", routeId);
        routeService.deleteRoute(routeId);
        log.info("Route deleted with ID: {}", routeId);
        return ResponseEntity.ok("Route deleted successfully for ID: " + routeId);
    }
    
    
    @GetMapping("/getAll")
    public ResponseEntity<List<RouteDTO>> getAllRoutes() {
        log.info("Fetching all Routes");
        List<Route> routes = routeService.getAllRoutes();
        
        List<RouteDTO> routeDTOs = routes.stream().map(route -> {
            RouteDTO dto = new RouteDTO();
            dto.setRoute_id(route.getRoute_id());
            dto.setOrigin(route.getOrigin());
            dto.setDestination(route.getDestination());
            dto.setDepartureTime(route.getDepartureTime());
            dto.setArrivalTime(route.getArrivalTime());
            dto.setBaseFare(route.getBaseFare());
            dto.setScheduleDate(route.getScheduleDate()); // in getAllRoutes and flightCode endpoint
            dto.setFlightCode(route.getFlight().getFlightCode()); // assuming getFlight() is not null
            return dto;
        }).toList();
        
        return ResponseEntity.ok(routeDTOs);
    }
    
    @GetMapping("/flightCode/{flightCode}")
    public ResponseEntity<List<RouteDTO>> getRoutesByFlightCode(@PathVariable String flightCode) {
        List<Route> routes = routeService.getRoutesByFlightCode(flightCode);

        List<RouteDTO> dtoList = routes.stream().map(route -> {
            RouteDTO dto = new RouteDTO();
            dto.setRoute_id(route.getRoute_id());
            dto.setOrigin(route.getOrigin());
            dto.setDestination(route.getDestination());
            dto.setDepartureTime(route.getDepartureTime());
            dto.setArrivalTime(route.getArrivalTime());
            dto.setBaseFare(route.getBaseFare());
            dto.setFlightCode(route.getFlight().getFlightCode());
            return dto;
        }).toList();

        return ResponseEntity.ok(dtoList);
    }


    @GetMapping("/{routeId}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long routeId) throws RouteNotFoundException {
        log.info("Fetching Route with ID: {}", routeId);
        Route route = routeService.getRouteById(routeId);
        log.info("Route fetched: {}", route);
        return ResponseEntity.ok(route);
    }

    @GetMapping("/route-flight-info")
    public ResponseEntity<RouteFlightDTO> getRouteFlightInfo(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String flightCode) throws RouteNotFoundException {

        RouteFlightDTO dto = routeService.getRouteFlightDetails(origin, destination, flightCode);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<List<Route>> getRoutesByDate(
            @PathVariable @org.springframework.format.annotation.DateTimeFormat(
                    iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
            java.time.LocalDate date) {

        List<Route> routes = routeService.getRoutesByDate(date);
        return ResponseEntity.ok(routes);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<RouteDTO>> getAllRoutes(Authentication auth)
            throws UserNotFoundException, FlightOwnerNotFoundException {

        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findByUsername(auth.getName());
        List<Route> routes;

        if (user.getRoles().contains("ROLE_ADMIN")) {

            routes = routeService.getAllRoutes();

        } 
        else if (user.getRoles().contains("ROLE_FLIGHT_OWNER")) {

            FlightOwner owner = flightOwnerRepository.findByUser(user)
                    .orElseThrow(() -> new FlightOwnerNotFoundException(
                            "No flight owner for user " + user.getUsername()));

            routes = routeService.getRoutesByOwner(owner.getOwnerId());
        } 
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<RouteDTO> dtos = routes.stream().map(route -> {
            RouteDTO dto = new RouteDTO();
            dto.setRoute_id(route.getRoute_id());
            dto.setOrigin(route.getOrigin());
            dto.setDestination(route.getDestination());
            dto.setDepartureTime(route.getDepartureTime());
            dto.setArrivalTime(route.getArrivalTime());
            dto.setBaseFare(route.getBaseFare());
            dto.setScheduleDate(route.getScheduleDate());
            dto.setFlightCode(route.getFlight().getFlightCode());
            return dto;
        }).toList();

        return ResponseEntity.ok(dtos);
    }

}