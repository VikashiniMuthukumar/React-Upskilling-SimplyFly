package com.hexaware.simplyfly.services;

import java.util.ArrayList;

/**
 * Service for managing bookings.
 * Handles booking creation, update, deletion, and retrieval.
 * Author: Vikashini
 * Version: 1.0
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.entities.Booking;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.entities.Route;
import com.hexaware.simplyfly.enums.BookingStatus;
import com.hexaware.simplyfly.exceptions.BookingNotFoundException;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.repositories.BookingRepository;
import com.hexaware.simplyfly.repositories.FlightRepository;
import com.hexaware.simplyfly.repositories.RouteRepository;
import com.hexaware.simplyfly.repositories.UserRepository;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private FlightRepository flightRepository;


	
//	@Override
//	public Booking createBooking(BookingDTO dto) throws UserNotFoundException, RouteNotFoundException {
//	    Booking booking = new Booking();
//	  //  booking.setBookingId(dto.getBookingId());
//	    booking.setBookedAt(dto.getBookedAt());
//	    booking.setStatus(dto.getStatus());
//	    booking.setTotalFare(dto.getTotalFare());
//	    booking.setUsername(dto.getUsername());
//	    booking.setOrigin(dto.getOrigin());
//	    booking.setDestination(dto.getDestination());
//	    booking.setBookDate(dto.getBookDate());
//	    booking.setSeat(dto.getSeat());
//	    booking.setBaseFare(dto.getBaseFare());
//	    booking.setArrivalTime(dto.getArrivalTime());
//	    booking.setDepartureTime(dto.getDepartureTime());
//	    booking.setCheckInBaggageLimit(dto.getCheckInBaggageLimit());
//	    booking.setCabinBaggageLimit(dto.getCabinBaggageLimit());
//
//
//	    if (dto.getFlightCode() != null) {
//	        Flight flight = flightRepository.findByFlightCode(dto.getFlightCode())
//	            .orElseThrow(() -> new RuntimeException("Flight not found with code: " + dto.getFlightCode()));
//	        booking.setFlight(flight);
//
//	        // ✅ ADD THESE LINES TO POPULATE COLUMNS
//	        booking.setFlightCode(flight.getFlightCode());
//	        booking.setFlightName(flight.getName());
//	    }
//
//	    return bookingRepository.save(booking);
//	}

	
//	@Override
//	public Booking createBooking(BookingDTO dto) throws UserNotFoundException, RouteNotFoundException {
//
////	    Booking booking = new Booking();
////
////	    booking.setBookedAt(dto.getBookedAt());
////	    booking.setStatus(dto.getStatus());
////	    booking.setTotalFare(dto.getTotalFare());
////	    booking.setUsername(dto.getUsername());
////	    booking.setOrigin(dto.getOrigin());
////	    booking.setDestination(dto.getDestination());
////	    booking.setBookDate(dto.getBookDate());
////	    booking.setSeat(dto.getSeat());
////	    booking.setBaseFare(dto.getBaseFare());
////	    booking.setArrivalTime(dto.getArrivalTime());
////	    booking.setDepartureTime(dto.getDepartureTime());
////	    booking.setCheckInBaggageLimit(dto.getCheckInBaggageLimit());
////	    booking.setCabinBaggageLimit(dto.getCabinBaggageLimit());
////
////	    if(dto.getFlightCode()!=null){
////
////	        Flight flight = flightRepository.findByFlightCode(dto.getFlightCode())
////	                .orElseThrow(() -> new RuntimeException("Flight not found"));
////
////	        if (flight.getTotalSeats() < booking.getSeat()) {
////	            throw new RuntimeException("Not enough seats available");
////	        }
////
////	        // reduce seats
////	        flight.setTotalSeats(
////	            flight.getTotalSeats() - booking.getSeat()
////	        );
////	        flightRepository.save(flight);
////
////	        booking.setFlight(flight);
////	        booking.setFlightCode(flight.getFlightCode());
////	        booking.setFlightName(flight.getName());
////	    }
////
////	    return bookingRepository.save(booking);
//		
//	Flight flight = flightRepository.findByFlightCode(dto.getFlightCode());
//
//        if (flight == null) {
//            throw new RuntimeException("Flight not found");
//        }
//
//        if (dto.getSeat() <= 0 || dto.getSeat() > 6) {
//            throw new RuntimeException("Maximum 6 seats allowed per booking");
//        }
//
//        if (flight.getTotalSeats() < dto.getSeat()) {
//            throw new RuntimeException("Not enough seats available");
//        }
//
//        // Deduct seats
//        flight.setTotalSeats(flight.getTotalSeats() - dto.getSeat());
//        flightRepository.save(flight);
//
//        Booking booking = new Booking();
//
//        booking.setUsername(dto.getUsername());
//        booking.setFlightCode(dto.getFlightCode());
//        booking.setFlightName(dto.getFlightName());
//        booking.setOrigin(dto.getOrigin());
//        booking.setDestination(dto.getDestination());
//        booking.setSeat(dto.getSeat());
//        booking.setBookDate(dto.getBookDate());
//
//        booking.setBaseFare(dto.getBaseFare());
//        booking.setTotalFare(dto.getBaseFare() * dto.getSeat());
//
//        booking.setDepartureTime(dto.getDepartureTime());
//        booking.setArrivalTime(dto.getArrivalTime());
//
//        booking.setStatus("CONFIRMED");
//
//        return bookingRepository.save(booking);
//    }
//	
//

//	@Override
//	public Booking createBooking(BookingDTO dto) {
//
//	    Flight flight = flightRepository
//	            .findByFlightCode(dto.getFlightCode())
//	            .orElseThrow(() -> new RuntimeException("Flight not found"));
//
//	    if (dto.getSeat() <= 0 || dto.getSeat() > 6) {
//	        throw new RuntimeException("Maximum 6 seats allowed per booking");
//	    }
//
//	    if (flight.getTotalSeats() < dto.getSeat()) {
//	        throw new RuntimeException("Not enough seats available");
//	    }
//
//	    // Deduct seats
//	    flight.setTotalSeats(flight.getTotalSeats() - dto.getSeat());
//	    flightRepository.save(flight);
//
//	    Booking booking = new Booking();
//
//	    booking.setUsername(dto.getUsername());
//	    booking.setFlightCode(dto.getFlightCode());
//	    booking.setFlightName(dto.getFlightName());
//	    booking.setOrigin(dto.getOrigin());
//	    booking.setDestination(dto.getDestination());
//
//	    booking.setSeat(dto.getSeat());
//	    booking.setBookDate(dto.getBookDate());
//
//	    booking.setBaseFare(dto.getBaseFare());
//	    booking.setTotalFare(dto.getBaseFare() * dto.getSeat());
//
//	    booking.setDepartureTime(dto.getDepartureTime());
//	    booking.setArrivalTime(dto.getArrivalTime());
//
//	    booking.setStatus(BookingStatus.CONFIRMED);
//	    
//	    
//
//	    return bookingRepository.save(booking);
//	}
	
	
	@Override
	public Booking createBooking(BookingDTO dto) {

	    Flight flight = flightRepository
	            .findByFlightCode(dto.getFlightCode())
	            .orElseThrow(() -> new RuntimeException("Flight not found"));

	    if (dto.getSeat() <= 0 || dto.getSeat() > 6) {
	        throw new RuntimeException("Maximum 6 seats allowed per booking");
	    }

	    if (flight.getTotalSeats() < dto.getSeat()) {
	        throw new RuntimeException("Not enough seats available");
	    }

	    // deduct seats
	    flight.setTotalSeats(flight.getTotalSeats() - dto.getSeat());
	    flightRepository.save(flight);

	    Booking booking = new Booking();

	    booking.setUsername(dto.getUsername());

	    // ⭐ VERY IMPORTANT
	    booking.setFlight(flight);

	    booking.setFlightCode(flight.getFlightCode());
	    booking.setFlightName(flight.getName());

	    booking.setOrigin(dto.getOrigin());
	    booking.setDestination(dto.getDestination());

	    booking.setSeat(dto.getSeat());
	    booking.setBookDate(dto.getBookDate());

	    booking.setBaseFare(dto.getBaseFare());
	    booking.setTotalFare(dto.getBaseFare() * dto.getSeat());

	    booking.setDepartureTime(dto.getDepartureTime());
	    booking.setArrivalTime(dto.getArrivalTime());

	    booking.setStatus(BookingStatus.CONFIRMED);

	    return bookingRepository.save(booking);
	}
	
	
	
	@Override
	public Booking updateBooking(Long booking_id, BookingDTO dto)
	        throws BookingNotFoundException, UserNotFoundException, RouteNotFoundException {
	    Booking booking = bookingRepository.findById(booking_id)
	            .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + booking_id));

	    booking.setStatus(dto.getStatus());
	    booking.setTotalFare(dto.getTotalFare());
	    booking.setSeat(dto.getSeat());
	    booking.setBaseFare(dto.getBaseFare()); // ✅ IMPORTANT

	    return bookingRepository.save(booking);
	}

//	@Override
//	public boolean deleteBooking(Long booking_id) throws BookingNotFoundException {
//		if (!bookingRepository.existsById(booking_id)) {
//			throw new BookingNotFoundException("Booking not found with ID: " + booking_id);
//		}
//		bookingRepository.deleteById(booking_id);
//		return true;
//	}
	
	
	@Override
	public boolean deleteBooking(Long bookingId) {

	    Booking booking = bookingRepository.findById(bookingId)
	            .orElseThrow(() -> new RuntimeException("Booking not found"));

	    // get flight using flightCode
	    Flight flight = flightRepository
	            .findByFlightCode(booking.getFlightCode())
	            .orElseThrow(() -> new RuntimeException("Flight not found"));

	    int seatsBooked = booking.getSeat();

	    // increase seats back
	    flight.setTotalSeats(flight.getTotalSeats() + seatsBooked);

	    flightRepository.save(flight);

	    // delete booking
	    bookingRepository.delete(booking);
		return true;
	}

	@Override
	public Booking getBookingById(Long booking_id) throws BookingNotFoundException {
		return bookingRepository.findById(booking_id)
				.orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + booking_id));
	}


	
	@Override
	public List<BookingDTO> getAllBookings() {
	    List<Booking> bookings = bookingRepository.findAll();
	    List<BookingDTO> dtoList = new ArrayList<>();

	    for (Booking b : bookings) {
	        BookingDTO dto = new BookingDTO();
	        dto.setBookingId(b.getBookingId());
	        dto.setBookedAt(b.getBookedAt());
	        dto.setBookDate(b.getBookDate());
	        dto.setTotalFare(b.getTotalFare());
	        dto.setStatus(b.getStatus());
	        dto.setUsername(b.getUsername());
	        dto.setOrigin(b.getOrigin());
	        dto.setDestination(b.getDestination());
	        dto.setSeat(b.getSeat());
	        dto.setBaseFare(b.getBaseFare());
	        dto.setArrivalTime(b.getArrivalTime());
	        dto.setDepartureTime(b.getDepartureTime());
	        dto.setCabinBaggageLimit(b.getCabinBaggageLimit());
	        dto.setCheckInBaggageLimit(b.getCheckInBaggageLimit());

	        dto.setFlightCode(b.getFlightCode());
	        dto.setFlightName(b.getFlightName());

	        dtoList.add(dto);
	    }

	    return dtoList;
	}


	
	@Override
	public BookingDTO convertToDTO(Booking booking) {
	    BookingDTO dto = new BookingDTO();
	    dto.setBookingId(booking.getBookingId());
	    dto.setBookedAt(booking.getBookedAt());
	    dto.setStatus(booking.getStatus());
	    dto.setBaseFare(booking.getBaseFare());
	    dto.setTotalFare(booking.getTotalFare());
	    dto.setUsername(booking.getUsername());
	    dto.setOrigin(booking.getOrigin());
	    dto.setDestination(booking.getDestination());
	    dto.setBookDate(booking.getBookDate());
	    dto.setFlightCode(booking.getFlightCode());
	    dto.setFlightName(booking.getFlightName());

	    // ✅ Add these lines
	    dto.setSeat(booking.getSeat());
	    dto.setArrivalTime(booking.getArrivalTime());
	    dto.setDepartureTime(booking.getDepartureTime());
	    dto.setCheckInBaggageLimit(booking.getCheckInBaggageLimit());
	    dto.setCabinBaggageLimit(booking.getCabinBaggageLimit());

	    return dto;
	}


	@Override
	public List<BookingDTO> getBookingsByStatus(BookingStatus status) {
	    List<Booking> bookings = bookingRepository.findByStatus(status);
	    List<BookingDTO> dtoList = new ArrayList<>();

	    for (Booking b : bookings) {
	        BookingDTO dto = new BookingDTO();
	        dto.setBookingId(b.getBookingId());
	        dto.setBookedAt(b.getBookedAt());
	        dto.setBookDate(b.getBookDate());
	        dto.setTotalFare(b.getTotalFare());
	        dto.setStatus(b.getStatus());
	        dto.setUsername(b.getUsername());
	        dto.setOrigin(b.getOrigin());
	        dto.setDestination(b.getDestination());

	        dto.setFlightCode(b.getFlightCode());
	        dto.setFlightName(b.getFlightName());

	        dtoList.add(dto);
	    }

	    return dtoList;
	}
	
	
	
//	@Override
//	public List<BookingDTO> getBookingsByUsernameAndStatus(String username, BookingStatus status) {
//
//	    List<Booking> bookings =
//	            bookingRepository.findByUsernameAndStatus(username, status);
//
//	    return bookings.stream()
//	            .map(this::convertToDTO)
//	            .toList();
//	}
	
	
	@Override
	public void cancelBooking(Long bookingId) throws BookingNotFoundException {

	    Booking booking = bookingRepository.findById(bookingId)
	            .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

	    // check already cancelled
	    if (booking.getStatus() == BookingStatus.CANCELLED) {
	        throw new RuntimeException("Booking already cancelled");
	    }

	    // get flight
	    Flight flight = flightRepository
	            .findByFlightCode(booking.getFlightCode())
	            .orElseThrow(() -> new RuntimeException("Flight not found"));

	    int seatsBooked = booking.getSeat();

	    // return seats
	    flight.setTotalSeats(flight.getTotalSeats() + seatsBooked);
	    flightRepository.save(flight);

	    // update status
	    booking.setStatus(BookingStatus.CANCELLED);

	    bookingRepository.save(booking);
	}
	
	
	@Override
	public List<BookingDTO> getBookingsByUsernameAndStatus(String username, BookingStatus status) {

	    List<Booking> bookings =
	            bookingRepository.findByUsernameAndStatus(username, status);

	    List<BookingDTO> dtoList = new ArrayList<>();

	    for (Booking b : bookings) {

	        BookingDTO dto = new BookingDTO();

	        dto.setBookingId(b.getBookingId());
	        dto.setUsername(b.getUsername());
	        dto.setBookDate(b.getBookDate());
	        dto.setBookedAt(b.getBookedAt());
	        dto.setSeat(b.getSeat());          // IMPORTANT
	        dto.setTotalFare(b.getTotalFare());
	        dto.setStatus(b.getStatus());

	        dto.setOrigin(b.getOrigin());
	        dto.setDestination(b.getDestination());

	        dto.setFlightCode(b.getFlightCode());
	        dto.setFlightName(b.getFlightName());

	        dtoList.add(dto);
	    }

	    return dtoList;
	}
	
	@Override
	public List<BookingDTO> getBookingsByUsername(String username) {

	    List<Booking> bookings = bookingRepository.findByUsername(username);

	    List<BookingDTO> dtoList = new ArrayList<>();

	    for (Booking b : bookings) {

	        BookingDTO dto = new BookingDTO();

	        dto.setBookingId(b.getBookingId());
	        dto.setUsername(b.getUsername());
	        dto.setBookDate(b.getBookDate());
	        dto.setBookedAt(b.getBookedAt());
	        dto.setSeat(b.getSeat());
	        dto.setTotalFare(b.getTotalFare());
	        dto.setStatus(b.getStatus());

	        dto.setOrigin(b.getOrigin());
	        dto.setDestination(b.getDestination());

	        dto.setFlightCode(b.getFlightCode());
	        dto.setFlightName(b.getFlightName());

	        dto.setArrivalTime(b.getArrivalTime());
	        dto.setDepartureTime(b.getDepartureTime());

	        dto.setCabinBaggageLimit(b.getCabinBaggageLimit());
	        dto.setCheckInBaggageLimit(b.getCheckInBaggageLimit());

	        dtoList.add(dto);
	    }

	    return dtoList;
	}
	
	

}
