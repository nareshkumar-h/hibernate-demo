package com.touristapp.dao;

import java.util.List;

import com.touristapp.model.Account;
import com.touristapp.model.User;

public class TestFetchUserAccounts {

	public static void main(String[] args) {

		User user = UserDAO.findById(38);
		System.out.println(user);
		
		//Test using LAZY + EAGER
//		List<Account> accounts = user.getAccounts();
//		System.out.println(accounts);
	}

}
