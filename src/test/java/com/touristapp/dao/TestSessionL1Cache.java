package com.touristapp.dao;

import org.hibernate.Session;

import com.touristapp.model.User;
import com.touristapp.util.HibernateUtil;

public class TestSessionL1Cache {

	public static void main(String[] args) {
		
		int userId = 1;
		 
		Session session = HibernateUtil.getSession(); //Create Session
		
		/* Within the same session, 
		 * we are going to retrieve  user details twice.
		 * 1st time, it will retrieve from db and store it in session cache.
		 * 2nd time, it will retrieve from L1 session cache
		 */
		System.out.println("##### Get User - 1st time #### ");
		User userObj1 = session.find(User.class, userId);
		System.out.println(userObj1);	
		
		System.out.println("##### Get User - 2nd time #### ");
		User userObj2 = session.find(User.class, userId);
		System.out.println(userObj2);
		
		session.close();
		
	}

}
