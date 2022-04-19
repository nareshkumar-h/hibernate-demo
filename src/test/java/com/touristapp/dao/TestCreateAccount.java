package com.touristapp.dao;

import com.touristapp.model.Account;
import com.touristapp.model.User;

public class TestCreateAccount {

	public static void main(String[] args) {
		
		User user = UserDAO.findById(38);
		
		//create account
		Account account = new Account();
		account.setUser(user);
		account.setAccountType("SAVINGS");
		account.setBalance(1000);
		
		AccountDAO.save(account);
		
	}
}
