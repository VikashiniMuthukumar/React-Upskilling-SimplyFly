package com.hexaware.simplyfly.services;

/**
 * Service for managing FlightOwner entities.
 * Supports creation, update, deletion, and retrieval of flight owners.
 * Author: Vikashini
 * Version: 1.0
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.FlightOwnerDTO;
import com.hexaware.simplyfly.entities.FlightOwner;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.FlightOwnerNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.repositories.FlightOwnerRepository;
import com.hexaware.simplyfly.repositories.UserRepository;

@Service
public class FlightOwnerServiceImpl implements IFlightOwnerService {

//    @Autowired
//    private FlightOwnerRepository flightOwnerRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public FlightOwner createFlightOwner(FlightOwnerDTO dto) throws UserNotFoundException {
//        User user = userRepository.findById(dto.getUser_id())
//                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getUser_id()));
//
//        FlightOwner owner = new FlightOwner();
//        owner.setUser(user);  
//
//        return flightOwnerRepository.save(owner);
//
//    }
//
//    @Override
//    public FlightOwner updateFlightOwner(Long owner_id, FlightOwnerDTO dto) throws FlightOwnerNotFoundException, UserNotFoundException {
//        FlightOwner owner = flightOwnerRepository.findById(owner_id)
//                .orElseThrow(() -> new FlightOwnerNotFoundException("Flight Owner not found with ID: " + owner_id));
//
//        User user = userRepository.findById(dto.getUser_id())
//                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getUser_id()));
//
//        owner.setUser(user);
//
//        return flightOwnerRepository.save(owner);
//    }
//
//    @Override
//    public boolean deleteFlightOwner(Long owner_id) throws FlightOwnerNotFoundException {
//        FlightOwner owner = flightOwnerRepository.findById(owner_id)
//                .orElseThrow(() -> new FlightOwnerNotFoundException("Flight Owner not found with ID: " + owner_id));
//
//        if (!owner.getFlights().isEmpty()) {
//            throw new RuntimeException("Cannot delete FlightOwner with active flights. Delete or reassign flights first.");
//        }
//
//        flightOwnerRepository.delete(owner);
//        return true;
//    }
//
//
//    @Override
//    public FlightOwner getFlightOwnerById(Long owner_id) throws FlightOwnerNotFoundException {
//        return flightOwnerRepository.findById(owner_id)
//                .orElseThrow(() -> new FlightOwnerNotFoundException("Flight Owner not found with ID: " + owner_id));
//    }
//
//    @Override
//    public List<FlightOwner> getAllFlightOwners() {
//        return flightOwnerRepository.findAll();
//    }
}