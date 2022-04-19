package com.touristapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.touristapp.model.User;

public class TestAddUserUsingEntityManager {

	public static void main(String[] args) {

		//create user
		User user = new User();
        user.setName("Eswar");
        user.setEmail("eswar@gmail.com");
        user.setPassword("pass1234");
        user.setRole("ADMIN");
        
		
		//save user
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("touristapp-pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        em.close();
        
	}

}
