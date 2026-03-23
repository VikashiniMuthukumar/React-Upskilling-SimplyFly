package com.hexaware.simplyfly.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * RouteDTO carries route details with validation.
 * @author Vikashini
 * @version 1.0
 */

public class RouteDTO {

    private Long route_id;

    @NotNull
    @Size(max = 50, message = "Origin must be at most 50 characters")
    private String origin;

    @NotNull
    @Size(max = 50, message = "Destination must be at most 50 characters")
    private String destination;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;


    @NotNull
    @PositiveOrZero(message = "Base fare must be zero or positive")
    private Double baseFare;

    
    private String flightCode;
    

@NotNull(message = "Schedule date is required")
@JsonFormat(pattern = "yyyy-MM-dd")
private LocalDate scheduleDate;

    @JsonIgnore
    private List<BookingDTO> bookings;

    public RouteDTO() {}

    

    public RouteDTO(Long route_id,
			@NotNull @Size(max = 50, message = "Origin must be at most 50 characters") String origin,
			@NotNull @Size(max = 50, message = "Destination must be at most 50 characters") String destination,
			@NotNull LocalTime departureTime, LocalTime arrivalTime,
			@NotNull @PositiveOrZero(message = "Base fare must be zero or positive") Double baseFare, String flightCode,
			@NotNull(message = "Schedule date is required") LocalDate scheduleDate, List<BookingDTO> bookings) {
		super();
		this.route_id = route_id;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.baseFare = baseFare;
		this.flightCode = flightCode;
		this.scheduleDate = scheduleDate;
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
    
    

    public LocalDate getScheduleDate() {
		return scheduleDate;
	}



	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
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

    public Double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Double baseFare) {
        this.baseFare = baseFare;
    }

    
    public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }

	@Override
	public String toString() {
		return "RouteDTO [route_id=" + route_id + ", origin=" + origin + ", destination=" + destination
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", baseFare=" + baseFare
				+ ", flightCode=" + flightCode + ", bookings=" + bookings + "]";
	}

	
    
}