package com.touristapp.dao;

import com.touristapp.model.Account;
import com.touristapp.model.User;

public class TestCreateAccount2 {

	public static void main(String[] args) {
		
		//create user + create account (combined)
		User user =  new User();
		user.setName("Prabhu");
		user.setEmail("p@gmail.com");
		user.setPassword("pass1234");
		user.setRole("USER");
		
		//create account
		Account account = new Account();
		account.setUser(user);
		account.setAccountType("SAVINGS");
		account.setBalance(1000);
		
		AccountDAO.save(account);
		
	}
}
