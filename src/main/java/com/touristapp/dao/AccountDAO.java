package com.touristapp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.touristapp.model.Account;
import com.touristapp.util.HibernateUtil;

public class AccountDAO {

	public static void save(Account account) {

		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		try {
			session.persist(account);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
	
	}
}
