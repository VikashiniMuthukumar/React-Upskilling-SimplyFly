//package com.hexaware.simplyfly.entities;
//
//import java.time.LocalDateTime;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.hexaware.simplyfly.enums.BookingStatus;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
//
///**
// * Entity representing a Booking made by a User on a Route.
// * Author: Vikashini
// * Version: 1.0
// */
//
//@Entity
//public class Booking {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "booking_id")
//    private Long bookingId;
//
//    private LocalDateTime bookedAt = LocalDateTime.now();
//
//    @Enumerated(EnumType.STRING)
//    private BookingStatus status = BookingStatus.CONFIRMED;
//
//    private Double totalFare;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "route_id")	
//    @JsonManagedReference
//    private Route route;
//
//
//	public Booking() {
//		super();
//	}
//
//
//	public Booking(Long bookingId, LocalDateTime bookedAt, BookingStatus status, Double totalFare, User user,
//			Route route) {
//		super();
//		this.bookingId = bookingId;
//		this.bookedAt = bookedAt;
//		this.status = status;
//		this.totalFare = totalFare;
//		this.user = user;
//		this.route = route;
//	}
//
//
//	public Long getBookingId() {
//		return bookingId;
//	}
//
//
//	public void setBookingId(Long bookingId) {
//		this.bookingId = bookingId;
//	}
//
//
//	public LocalDateTime getBookedAt() {
//		return bookedAt;
//	}
//
//
//	public void setBookedAt(LocalDateTime bookedAt) {
//		this.bookedAt = bookedAt;
//	}
//
//
//	public BookingStatus getStatus() {
//		return status;
//	}
//
//
//	public void setStatus(BookingStatus status) {
//		this.status = status;
//	}
//
//
//	public Double getTotalFare() {
//		return totalFare;
//	}
//
//
//	public void setTotalFare(Double totalFare) {
//		this.totalFare = totalFare;
//	}
//
//
//	public User getUser() {
//		return user;
//	}
//
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//
//	public Route getRoute() {
//		return route;
//	}
//
//
//	public void setRoute(Route route) {
//		this.route = route;
//	}
//
//
//	@Override
//	public String toString() {
//		return "Booking [bookingId=" + bookingId + ", bookedAt=" + bookedAt + ", status=" + status + ", totalFare="
//				+ totalFare + ", user=" + user + ", route=" + route + "]";
//	}
//
//	
//   
//}

package com.hexaware.simplyfly.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hexaware.simplyfly.enums.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long bookingId;

	private LocalDateTime bookedAt = LocalDateTime.now();

	private LocalDate bookDate; 

	private String username; 

	private int seat;
	private String origin; 

	private String destination; 

	private String flightName;

	private String flightCode;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private int checkInBaggageLimit;
	private int cabinBaggageLimit;
	private String passengerName;
	private int passengerAge;


	@Enumerated(EnumType.STRING)
	private BookingStatus status = BookingStatus.APPLIED;

	private Double baseFare;
	private Double totalFare;

	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;

	@ManyToOne
	@JoinColumn(name = "id")
	@JsonBackReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "route_id")
	@JsonManagedReference
	private Route route;

	public Booking() {
		super();
	}

	
	public Booking(Long bookingId, LocalDateTime bookedAt, LocalDate bookDate, String username, int seat, String origin,
			String destination, String flightName, String flightCode, LocalTime arrivalTime, LocalTime departureTime,
			int checkInBaggageLimit, int cabinBaggageLimit, BookingStatus status, Double baseFare, Double totalFare,
			Flight flight, User user, Route route) {
		super();
		this.bookingId = bookingId;
		this.bookedAt = bookedAt;
		this.bookDate = bookDate;
		this.username = username;
		this.seat = seat;
		this.origin = origin;
		this.destination = destination;
		this.flightName = flightName;
		this.flightCode = flightCode;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.checkInBaggageLimit = checkInBaggageLimit;
		this.cabinBaggageLimit = cabinBaggageLimit;
		this.status = status;
		this.baseFare = baseFare;
		this.totalFare = totalFare;
		this.flight = flight;
		this.user = user;
		this.route = route;
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

	public LocalDate getBookDate() {
		return bookDate;
	}

	public void setBookDate(LocalDate bookDate) {
		this.bookDate = bookDate;
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

	// Existing getters and setters...

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
		
	  public User getUser() { return user; }
	  
	  public void setUser(User user) { this.user = user; }
	 
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	
	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
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
		return "Booking [bookingId=" + bookingId + ", bookedAt=" + bookedAt + ", bookDate=" + bookDate + ", username="
				+ username + ", seat=" + seat + ", origin=" + origin + ", destination=" + destination + ", flightName="
				+ flightName + ", flightCode=" + flightCode + ", arrivalTime=" + arrivalTime + ", departureTime="
				+ departureTime + ", checkInBaggageLimit=" + checkInBaggageLimit + ", cabinBaggageLimit="
				+ cabinBaggageLimit + ", passengerName=" + passengerName + ", passengerAge=" + passengerAge
				+ ", status=" + status + ", baseFare=" + baseFare + ", totalFare=" + totalFare + ", flight=" + flight
				+ ", user=" + user + ", route=" + route + "]";
	}



	

}
