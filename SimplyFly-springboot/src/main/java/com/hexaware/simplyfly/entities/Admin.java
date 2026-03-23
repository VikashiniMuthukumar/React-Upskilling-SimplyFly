package com.hexaware.simplyfly.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
// or javax.persistence.* depending on your setup
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing an Admin with a linked User.
 * Author: Vikashini
 * Version: 1.0
 */

@Entity
@Table(name = "admin")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "admin_id")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "admin_id")
    private long admin_id;  

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    public Admin() {
    }

	public long getAdmin_id() {
		return admin_id;
	}


	public void setAdmin_id(long admin_id) {
		this.admin_id = admin_id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", user=" + user + "]";
	}

    
}