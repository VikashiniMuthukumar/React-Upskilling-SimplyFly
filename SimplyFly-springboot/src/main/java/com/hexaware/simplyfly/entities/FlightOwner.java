package com.hexaware.simplyfly.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

/**
 * Entity representing a Flight Owner who is linked to a User and can have multiple flights.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

@Entity
public class FlightOwner {
    @Id
    @Column(name = "owner_id")
    private Long ownerId;

    @OneToOne
    @JsonIgnoreProperties({"flightOwner"})
    @MapsId
    private User user;


	public FlightOwner() {
		super();
	}

	

	public Long getOwnerId() {
		return ownerId;
	}



	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}



	public FlightOwner(Long ownerId, User user) {
		super();
		this.ownerId = ownerId;
		this.user = user;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "FlightOwner [ownerId=" + ownerId + ", user=" + user + "]";
	}

	
	
}
