package com.touristapp.dao;

import com.touristapp.model.User;

public class TestGetUser {

	public static void main(String[] args) {
		
		User user = UserDAO.findById(1);
		System.out.println(user);
	}
}
