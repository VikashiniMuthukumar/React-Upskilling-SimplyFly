package com.hexaware.simplyfly.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.hexaware.simplyfly.enums.BookingStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * BookingDTO carries booking details with validation.
 * 
 * @author Vikashini
 * @version 1.0
 */

public class BookingDTO {

	private Long bookingId;

	@NotNull
	private LocalDateTime bookedAt;

	@NotNull
	private BookingStatus status;

	private Double baseFare;
	@NotNull
	@Positive
	private Double totalFare;

	@NotBlank
	private String username;

	@NotBlank
	private String origin;

	@NotBlank
	private String destination;

	private int seat;
	@NotNull
	private LocalDate bookDate;

	@NotBlank
	private String flightCode;

	@NotBlank
	private String flightName;
	
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private int checkInBaggageLimit;
	private int cabinBaggageLimit;
	private String passengerName;
	private int passengerAge;
	
//	private Double fare;
//	private String departureTime;
//	private String arrivalTime;
//
//	private int cabinBaggageLimit;
//	private int checkInBaggageLimit;


	public BookingDTO() {
		super();
	}

	


	public BookingDTO(Long bookingId, @NotNull LocalDateTime bookedAt, @NotNull BookingStatus status, Double baseFare,
		@NotNull @Positive Double totalFare, @NotBlank String username, @NotBlank String origin,
		@NotBlank String destination, int seat, @NotNull LocalDate bookDate, @NotBlank String flightCode,
		@NotBlank String flightName, LocalTime arrivalTime, LocalTime departureTime, int checkInBaggageLimit,
		int cabinBaggageLimit) {
	super();
	this.bookingId = bookingId;
	this.bookedAt = bookedAt;
	this.status = status;
	this.baseFare = baseFare;
	this.totalFare = totalFare;
	this.username = username;
	this.origin = origin;
	this.destination = destination;
	this.seat = seat;
	this.bookDate = bookDate;
	this.flightCode = flightCode;
	this.flightName = flightName;
	this.arrivalTime = arrivalTime;
	this.departureTime = departureTime;
	this.checkInBaggageLimit = checkInBaggageLimit;
	this.cabinBaggageLimit = cabinBaggageLimit;
}


	public int getSeat() {
		return seat;
	}

	public Double getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(Double baseFare) {
		this.baseFare = baseFare;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDateTime getBookedAt() {
		return bookedAt;
	}

	public void setBookedAt(LocalDateTime bookedAt) {
		this.bookedAt = bookedAt;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public Double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}


	public LocalDate getBookDate() {
		return bookDate;
	}

	public void setBookDate(LocalDate bookDate) {
		this.bookDate = bookDate;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	
	

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}




	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}




	public LocalTime getDepartureTime() {
		return departureTime;
	}




	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}




	public int getCheckInBaggageLimit() {
		return checkInBaggageLimit;
	}




	public void setCheckInBaggageLimit(int checkInBaggageLimit) {
		this.checkInBaggageLimit = checkInBaggageLimit;
	}




	public int getCabinBaggageLimit() {
		return cabinBaggageLimit;
	}




	public void setCabinBaggageLimit(int cabinBaggageLimit) {
		this.cabinBaggageLimit = cabinBaggageLimit;
	}

	


	public String getPassengerName() {
		return passengerName;
	}




	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}




	public int getPassengerAge() {
		return passengerAge;
	}




	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}




	@Override
	public String toString() {
		return "BookingDTO [bookingId=" + bookingId + ", bookedAt=" + bookedAt + ", status=" + status + ", baseFare="
				+ baseFare + ", totalFare=" + totalFare + ", username=" + username + ", origin=" + origin
				+ ", destination=" + destination + ", seat=" + seat + ", bookDate=" + bookDate + ", flightCode="
				+ flightCode + ", flightName=" + flightName + ", arrivalTime=" + arrivalTime + ", departureTime="
				+ departureTime + ", checkInBaggageLimit=" + checkInBaggageLimit + ", cabinBaggageLimit="
				+ cabinBaggageLimit + ", passengerName=" + passengerName + ", passengerAge=" + passengerAge + "]";
	}




}
