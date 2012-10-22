package org.hibernate.hduo;

import java.util.List;

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

	public void insert() {

		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Team team = new Team("team111");
		Player p1 = new Player("p11");
		Player p2 = new Player("p12");
		/*
		 * 
		 * team.getPlayers().add(p1); team.getPlayers().add(p2);
		 * team.getPlayers().add(p3);
		 * 
		 * session.save(team);
		 */p1.setTeam(team);
		p1.setTeam(team);
		p2.setTeam(team);
		session.save(p1);
		session.save(p2);

		Team team2 = new Team("team222");
		Player p21 = new Player("p21");
		Player p22 = new Player("p22");

		p21.setTeam(team2);
		p22.setTeam(team2);

		session.save(p21);
		session.save(p22);

		session.getTransaction().commit();
		session.close();

	}

	public void testGet() {

		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<Team> teams = (List<Team>) session.createQuery("from Team")
				.list();

		for (Team team : teams) {
			for (Player player : team.getPlayers()) {
				System.out.println(player.getName());
			}
		}

		session.getTransaction().commit();
		session.close();

	}
}
