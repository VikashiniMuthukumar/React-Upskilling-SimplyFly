package com.hexaware.simplyfly.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;


/**
 * FlightOwnerDTO carries flight owner details with validation.
 * @author Vikashini
 * @version 1.0
 */

public class FlightOwnerDTO {

    private Long owner_id;

    @NotNull(message = "User ID is required for Flight Owner")
    private Long user_id;

    @JsonIgnore
    private List<FlightDTO> flights;

    public FlightOwnerDTO() {}

    public FlightOwnerDTO(Long owner_id, Long user_id, List<FlightDTO> flights) {
        this.owner_id = owner_id;
        this.user_id = user_id;
        this.flights = flights;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDTO> flights) {
        this.flights = flights;
    }

	@Override
	public String toString() {
		return "FlightOwnerDTO [owner_id=" + owner_id + ", user_id=" + user_id + ", flights=" + flights + "]";
	}
    
    
}