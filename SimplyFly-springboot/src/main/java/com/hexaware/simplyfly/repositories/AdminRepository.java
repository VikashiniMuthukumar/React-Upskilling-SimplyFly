package com.hexaware.simplyfly.repositories;

/**
 * Repository interface for Admin entity.
 * Extends JpaRepository to provide CRUD operations and query capabilities.
 * 
 * Author: Vikashini
 * Version: 1.0
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
