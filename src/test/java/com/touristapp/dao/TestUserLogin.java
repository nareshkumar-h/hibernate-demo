package com.touristapp.dao;

import com.touristapp.model.User;

public class TestUserLogin {

	public static void main(String[] args) {
		User userLogin = UserDAO.findByEmailAndPassword("naresh@gmail.com", "password");
		System.out.println("FindByEmailAndPassword result:" + userLogin);

	}

}
