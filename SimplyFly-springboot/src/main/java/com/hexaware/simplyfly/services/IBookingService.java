package com.hexaware.simplyfly.services;

import java.util.List;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.entities.Booking;
import com.hexaware.simplyfly.enums.BookingStatus;
import com.hexaware.simplyfly.exceptions.BookingNotFoundException;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;

public interface IBookingService {
	Booking createBooking(BookingDTO dto) throws UserNotFoundException, RouteNotFoundException;

	Booking updateBooking(Long booking_id, BookingDTO dto) throws BookingNotFoundException, UserNotFoundException,
			RouteNotFoundException;

	boolean deleteBooking(Long booking_id) throws BookingNotFoundException;

	Booking getBookingById(Long booking_id) throws BookingNotFoundException;

	List<BookingDTO> getAllBookings();

	List<BookingDTO> getBookingsByStatus(BookingStatus status) throws BookingNotFoundException;
	 List<BookingDTO> getBookingsByUsername(String username);
	 BookingDTO convertToDTO(Booking booking);

	List<BookingDTO> getBookingsByUsernameAndStatus(String username, BookingStatus status);

	void cancelBooking(Long bookingId) throws BookingNotFoundException;

//	List<BookingDTO> getBookingsByUsernameAndStatusNew(String username, BookingStatus status);
}
