package com.hexaware.simplyfly.services;

/**
 * Service implementation for admin, user, and flight management.
 * Handles CRUD operations for Admins, delegated User and Flight operations.
 * Author: Vikashini
 * Version: 1.0
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.simplyfly.dto.AdminDTO;
import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.Admin;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.AdminNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.repositories.AdminRepository;
import com.hexaware.simplyfly.repositories.FlightRepository;
import com.hexaware.simplyfly.repositories.UserRepository;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FlightRepository flightRepository;

	// Admin management

	@Override
	public Admin registerAdmin(AdminDTO adminDTO) throws UserNotFoundException {
		User user = userRepository.findById(adminDTO.getUser_id())
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + adminDTO.getUser_id()));

		Admin admin = new Admin();
		admin.setUser(user);

		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdmin(Long adminId, AdminDTO adminDTO) throws AdminNotFoundException, UserNotFoundException {
		Admin existingAdmin = adminRepository.findById(adminId)
				.orElseThrow(() -> new AdminNotFoundException("Admin not found with id: " + adminId));

		User user = userRepository.findById(adminDTO.getUser_id())
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + adminDTO.getUser_id()));

		existingAdmin.setUser(user);

		return adminRepository.save(existingAdmin);
	}

	@Override
	public boolean deleteAdmin(Long adminId) throws AdminNotFoundException {
		if (!adminRepository.existsById(adminId)) {
			throw new AdminNotFoundException("Admin not found with id: " + adminId);
		}
		adminRepository.deleteById(adminId);
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Admin getAdminById(Long adminId) throws AdminNotFoundException {
		return adminRepository.findById(adminId)
				.orElseThrow(() -> new AdminNotFoundException("Admin not found with id: " + adminId));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}


	@Override
	public User addUser(UserDTO userDTO) {
		User user = new User();
		user.setPassword(userDTO.getPassword());
		user.setRoles(userDTO.getRole()); 
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Long userId, UserDTO userDTO) throws UserNotFoundException {
	    User existingUser = userRepository.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
	    existingUser.setPassword(userDTO.getPassword());
	    existingUser.setRoles(userDTO.getRole()); 
	    
	    return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(Long userId) throws UserNotFoundException {
		if (!userRepository.existsById(userId)) {
			throw new UserNotFoundException("User not found with id: " + userId);
		}
		userRepository.deleteById(userId);
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserById(Long userId) throws UserNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Delegated Flight management

	@Override
	public Flight addFlight(FlightDTO flightDTO) {
	    Flight flight = new Flight();
	    flight.setName(flightDTO.getName());
        flight.setFlightCode(flightDTO.getFlightCode());
        flight.setTotalSeats(flightDTO.getTotalSeats());
        flight.setCabinBaggageLimit(flightDTO.getCabinBaggageLimit());
        flight.setCheckInBaggageLimit(flightDTO.getCheckInBaggageLimit());
	    
	    return flightRepository.save(flight);
	}


	@Override
	public Flight updateFlight(Long flightId, FlightDTO flightDTO) {
		Flight existingFlight = flightRepository.findById(flightId).orElse(null);
		if (existingFlight == null) {
			return null;
		}
		return flightRepository.save(existingFlight);
	}

	@Override
	public void deleteFlight(Long flightId) {
		flightRepository.deleteById(flightId);
	}

	@Override
	@Transactional
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	@Transactional
	public Flight getFlightById(Long flightId) {
		return flightRepository.findById(flightId).orElse(null);
	}
}