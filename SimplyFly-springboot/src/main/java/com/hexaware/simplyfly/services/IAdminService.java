package com.hexaware.simplyfly.services;

import java.util.List;

import com.hexaware.simplyfly.dto.AdminDTO;
import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.Admin;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.AdminNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;

public interface IAdminService {

    // Admin management
    Admin registerAdmin(AdminDTO adminDTO) throws UserNotFoundException;
    Admin updateAdmin(Long adminId, AdminDTO adminDTO) throws AdminNotFoundException, UserNotFoundException;
    boolean deleteAdmin(Long adminId) throws AdminNotFoundException;
    Admin getAdminById(Long adminId) throws AdminNotFoundException;
    List<Admin> getAllAdmins();

    // Delegated User management
    User addUser(UserDTO userDTO);
    User updateUser(Long userId, UserDTO userDTO) throws UserNotFoundException;
    void deleteUser(Long userId) throws UserNotFoundException;
    User getUserById(Long userId) throws UserNotFoundException;
    List<User> getAllUsers();

    // Delegated Flight management
    Flight addFlight(FlightDTO flightDTO);
    Flight updateFlight(Long flightId, FlightDTO flightDTO);
    void deleteFlight(Long flightId);
    List<Flight> getAllFlights();
    Flight getFlightById(Long flightId);
}
