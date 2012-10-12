package org.hduo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Dao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
}
