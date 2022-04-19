package com.touristapp.dao;

import java.util.List;

import com.touristapp.model.User;

public class TestFindAllUsers {

	public static void main(String[] args) {
		List<User> users = UserDAO.findAll();
		for (User user : users) {
			System.out.println(user);
		}
	}
}
