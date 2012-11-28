package hibernate.association.unidirection.onetomany;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;

public class Main {
	public static void testCreate() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Team team = new Team();
		team.setName("teamA");

		Player player1 = new Player("playerA");
		Player player2 = new Player("playerB");

		team.getPlayers().add(player1);
		team.getPlayers().add(player2);

		session.save(team);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void testGet1() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Team team = (Team) session.get(Team.class, 1L);
		for (Player player : team.getPlayers()) {
			System.out.println(player);
		}
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void testGet2() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Team team = (Team) session.get(Team.class, 1L);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		for (Player player : team.getPlayers()) {
			System.out.println(player);
		}
	}
	public static void testGet3() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Team team = (Team) session.createQuery("from Team").uniqueResult();
		System.out.println(team.getPlayers());
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		for (Player player : team.getPlayers()) {
			System.out.println(player);
		}
	}

	public static void main(String[] args) {
		//testCreate();
		testGet3();
	}

}
