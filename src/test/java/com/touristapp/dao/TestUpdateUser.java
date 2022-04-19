package com.touristapp.dao;

import com.touristapp.model.User;

public class TestUpdateUser {

	public static void main(String[] args) {


		User userResult = UserDAO.findById(37);
		System.out.println(userResult);
		userResult.setPassword("pass1234");
		UserDAO.update(userResult);
		
	}

}
