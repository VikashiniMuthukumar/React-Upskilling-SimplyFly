package com.hexaware.simplyfly.restcontrollers;

/**
 * REST controller for managing FlightOwner entities.
 * Provides endpoints for CRUD operations.
 * 
 * Author: Vikashini
 * Version: 1.0
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.FlightOwnerDTO;
import com.hexaware.simplyfly.entities.FlightOwner;
import com.hexaware.simplyfly.exceptions.FlightOwnerNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.services.IFlightOwnerService;
import com.hexaware.simplyfly.entities.FlightOwner;
import com.hexaware.simplyfly.exceptions.FlightOwnerNotFoundException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/flightowners")
public class FlightOwnerRestController {

//    @Autowired
//    private IFlightOwnerService flightOwnerService;
//
//    @PostMapping
//    public ResponseEntity<String> createFlightOwner(@Valid @RequestBody FlightOwnerDTO dto) throws UserNotFoundException {
//        log.info("Creating FlightOwner with data: {}", dto);
//        FlightOwner owner = flightOwnerService.createFlightOwner(dto);
//        log.info("FlightOwner created with ID: {}", owner.getOwner_id());
//        return new ResponseEntity<>("FlightOwner created successfully with ID: " + owner.getOwner_id(), HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{ownerId}")
//    public ResponseEntity<String> updateFlightOwner(@PathVariable Long ownerId, @Valid @RequestBody FlightOwnerDTO dto) throws FlightOwnerNotFoundException, UserNotFoundException {
//        log.info("Updating FlightOwner with ID: {}", ownerId);
//        FlightOwner owner = flightOwnerService.updateFlightOwner(ownerId, dto);
//        log.info("FlightOwner updated: {}", owner);
//        return ResponseEntity.ok("FlightOwner updated successfully for ID: " + owner.getOwner_id());
//    }
//
//    @DeleteMapping("/{ownerId}")
//    public ResponseEntity<String> deleteFlightOwner(@PathVariable Long ownerId) throws FlightOwnerNotFoundException {
//        log.warn("Deleting FlightOwner with ID: {}", ownerId);
//        flightOwnerService.deleteFlightOwner(ownerId);
//        log.info("FlightOwner deleted with ID: {}", ownerId);
//        return ResponseEntity.ok("FlightOwner deleted successfully for ID: " + ownerId);
//    }
//
//    @GetMapping("/{ownerId}")
//    public ResponseEntity<Object> getFlightOwnerById(@PathVariable Long ownerId) throws FlightOwnerNotFoundException {
//        log.info("Fetching FlightOwner with ID: {}", ownerId);
//        FlightOwner owner = flightOwnerService.getFlightOwnerById(ownerId);
//        log.info("FlightOwner fetched: {}", owner);
//        return ResponseEntity.ok(owner);
//    }
//
//    @GetMapping
//    public ResponseEntity<Object> getAllFlightOwners() {
//        log.info("Fetching all FlightOwners");
//        List<FlightOwner> owners = flightOwnerService.getAllFlightOwners();
//        log.info("Total FlightOwners fetched: {}", owners.size());
//        return ResponseEntity.ok(owners);
//    }
}