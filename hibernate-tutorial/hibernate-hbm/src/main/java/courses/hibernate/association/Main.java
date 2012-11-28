package courses.hibernate.association;

import org.hibernate.Session;

import sun.net.www.content.text.plain;

import courses.hibernate.util.HibernateUtil;

public class Main {
	public static void testCreate() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Team team = new Team();

		team.setName("teamA");

		Player player1 = new Player("playerA");
		Player player2 = new Player("playerB");

		player1.setTeam(team);
		player2.setTeam(team);

		session.save(player1);
		session.save(player2);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void testGet() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Player player = (Player)session.get(Player.class, 1L);
		
		System.out.println(player.getName());
		System.out.println("-------------------------------");
		System.out.println(player.getTeam().getName());
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void main(String[] args) {
		//testCreate();
		testGet();
	}

}
