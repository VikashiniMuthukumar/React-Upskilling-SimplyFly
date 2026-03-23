
package com.hexaware.simplyfly.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Entity representing a flight route with origin, destination, schedule, fare, and bookings.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

@Entity
public class Route {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long route_id;

    private String origin;
    private String destination;

    private LocalTime departureTime;
    private LocalTime arrivalTime;

    private Double baseFare;
    private LocalDate scheduleDate;

    private int availableSeats;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Booking> bookings = new ArrayList<>();

	public Route() {
		super();
	}

	public Route(Long route_id, String origin, String destination, LocalTime departureTime,
			LocalTime arrivalTime, Double baseFare,LocalDate scheduleDate, Flight flight, List<Booking> bookings) {
		super();
		this.route_id = route_id;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.baseFare = baseFare;
		this.scheduleDate = scheduleDate;
		this.flight = flight;
		this.bookings = bookings;
	}

	public Long getRoute_id() {
		return route_id;
	}

	public void setRoute_id(Long route_id) {
		this.route_id = route_id;
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

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Double getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(Double baseFare) {
		this.baseFare = baseFare;
	}
	
	 public LocalDate getScheduleDate() {
	        return scheduleDate;
	    }

	    public void setScheduleDate(LocalDate scheduleDate) {
	        this.scheduleDate = scheduleDate;
	    }

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Route [route_id=" + route_id + ", origin=" + origin + ", destination=" + destination
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", baseFare=" + baseFare
				+ ", scheduleDate=" + scheduleDate + ", availableSeats=" + availableSeats + ", flight=" + flight
				+ ", bookings=" + bookings + "]";
	}

	
	

}
