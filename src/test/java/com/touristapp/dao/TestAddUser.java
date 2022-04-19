package com.touristapp.dao;

import com.touristapp.model.User;

public class TestAddUser {

	public static void main(String[] args) {

		User user = new User();
		user.setName("Manoj");
		user.setEmail("manoj@gmail.com");
		user.setPassword("password");
		user.setRole("USER");
		
		UserDAO.save(user);
		
	}

}
