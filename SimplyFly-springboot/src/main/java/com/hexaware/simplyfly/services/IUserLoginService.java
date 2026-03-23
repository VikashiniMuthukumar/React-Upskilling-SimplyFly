package com.hexaware.simplyfly.services;

import com.hexaware.simplyfly.entities.UserLogin;

public interface IUserLoginService {

	public void register(UserLogin user);
	public boolean validateUser(String username, String password);
}
