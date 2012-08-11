package org.hibernate.hduo;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest extends TestCase {
	private SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application
		sessionFactory = new Configuration().configure() // configures settings
															// from
															// hibernate.cfg.xml
				.buildSessionFactory();
	}

	@Override
	protected void tearDown() throws Exception {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	public void testInsert() {

		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Person person = new Person();

		person.setName("ttttttttt");

		session.save(person);

		session.getTransaction().commit();
		session.close();

	}


}
