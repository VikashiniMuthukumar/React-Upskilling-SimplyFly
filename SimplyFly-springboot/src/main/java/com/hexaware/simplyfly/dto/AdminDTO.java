package com.hexaware.simplyfly.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * AdminDTO carries admin details with validation.
 * @author Vikashini
 * @version 1.0
 */
public class AdminDTO {
    private Long admin_id;

    @NotNull
    @Positive
    private Long user_id;

    public AdminDTO() {
       super();
    }

    public AdminDTO(Long admin_id, Long user_id) {
        this.admin_id = admin_id;
        this.user_id = user_id;
    }

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

	@Override
	public String toString() {
		return "AdminDTO [admin_id=" + admin_id + ", user_id=" + user_id + "]";
	}
    
    
}