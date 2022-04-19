package com.touristapp.dao;

public class TestDeleteUser {

	public static void main(String[] args) {

		//delete user details and user accounts
		int userId = 38;
		UserDAO.delete(userId);
		
	}

}
