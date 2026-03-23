package com.hexaware.simplyfly.dto;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * FlightDTO carries flight details with validation.
 * 
 * @author Vikashini
 * @version 1.0
 */

public class FlightDTO {

	private Long flightId;

//	@NotBlank
//	@Size(max = 100, message = "Flight name must be at most 100 characters")
//	private String name;
//
//	@NotBlank
//	@Size(max = 20, message = "Flight code must be at most 20 characters")
//	private String flightCode;
//
//	@NotNull
//	@Min(value = 1, message = "Total seats must be at least 1")
//	private Integer totalSeats;
//
//	@NotNull
//	@Min(value = 0, message = "Cabin baggage limit cannot be negative")
//	private Integer cabinBaggageLimit;
//
//	@NotNull
//	@Min(value = 0, message = "Check-in baggage limit cannot be negative")
//	private Integer checkInBaggageLimit;

	@NotBlank
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Flight name must contain only alphabets")
	private String name;

	@NotBlank
	@Pattern(
	  regexp = "^[A-Z]{2}[0-9]{3}$",
	  message = "Flight code must be 2 uppercase letters followed by 3 digits"
	)
	private String flightCode;

	@NotNull
	@Min(value = 180, message = "Total seats must be at least 180")
	@Max(value = 232, message = "Total seats must not exceed 232")
	private Integer totalSeats;

	@NotNull
	@Max(value = 7, message = "Cabin baggage limit cannot exceed 7 kg")
	private Integer cabinBaggageLimit;

	@NotNull
	@Min(value = 15, message = "Check-in baggage must be at least 15 kg")
	@Max(value = 20, message = "Check-in baggage cannot exceed 20 kg")
	private Integer checkInBaggageLimit;
	
	
	private String origin;
	private String destination;
	private Double baseFare;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private Long ownerId;
	
	@JsonIgnore
	private List<RouteDTO> routes;

	public FlightDTO() {
	}

	

	public FlightDTO(Long flightId,
			@NotBlank @Pattern(regexp = "^[A-Za-z ]+$", message = "Flight name must contain only alphabets") String name,
			@NotBlank @Pattern(regexp = "^[A-Z]{2}[0-9]{3}$", message = "Flight code must be 2 uppercase letters followed by 3 digits") String flightCode,
			@NotNull @Min(value = 180, message = "Total seats must be at least 180") @Max(value = 232, message = "Total seats must not exceed 232") Integer totalSeats,
			@NotNull @Max(value = 7, message = "Cabin baggage limit cannot exceed 7 kg") Integer cabinBaggageLimit,
			@NotNull @Min(value = 15, message = "Check-in baggage must be at least 15 kg") @Max(value = 20, message = "Check-in baggage cannot exceed 20 kg") Integer checkInBaggageLimit,
			String origin, String destination, Double baseFare, LocalTime departureTime, LocalTime arrivalTime,
			Long ownerId, List<RouteDTO> routes) {
		super();
		this.flightId = flightId;
		this.name = name;
		this.flightCode = flightCode;
		this.totalSeats = totalSeats;
		this.cabinBaggageLimit = cabinBaggageLimit;
		this.checkInBaggageLimit = checkInBaggageLimit;
		this.origin = origin;
		this.destination = destination;
		this.baseFare = baseFare;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.ownerId = ownerId;
		this.routes = routes;
	}



	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Integer getCabinBaggageLimit() {
		return cabinBaggageLimit;
	}

	public void setCabinBaggageLimit(Integer cabinBaggageLimit) {
		this.cabinBaggageLimit = cabinBaggageLimit;
	}

	public Integer getCheckInBaggageLimit() {
		return checkInBaggageLimit;
	}

	public void setCheckInBaggageLimit(Integer checkInBaggageLimit) {
		this.checkInBaggageLimit = checkInBaggageLimit;
	}

	public List<RouteDTO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteDTO> routes) {
		this.routes = routes;
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

	public Double getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(Double baseFare) {
		this.baseFare = baseFare;
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

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Override
	public String toString() {
		return "FlightDTO [flightId=" + flightId + ", name=" + name + ", flightCode=" + flightCode + ", totalSeats="
				+ totalSeats + ", cabinBaggageLimit=" + cabinBaggageLimit + ", checkInBaggageLimit="
				+ checkInBaggageLimit + ", origin=" + origin + ", destination=" + destination + ", baseFare=" + baseFare
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", routes=" + routes + "]";
	}
	public Long getOwnerId() {
	    return ownerId;
	}

	public void setOwnerId(Long ownerId) {
	    this.ownerId = ownerId;
	}

//	@Override
//	public String toString() {
//		return "FlightDTO [flightId=" + flightId + ", name=" + name + ", flightCode=" + flightCode + ", totalSeats="
//				+ totalSeats + ", cabinBaggageLimit=" + cabinBaggageLimit + ", checkInBaggageLimit="
//				+ checkInBaggageLimit +", routes=" + routes + "]";
//	}

	
}