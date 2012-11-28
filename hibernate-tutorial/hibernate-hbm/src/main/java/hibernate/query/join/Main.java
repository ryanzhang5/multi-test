package hibernate.query.join;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;

public class Main {
	public static void testCreate() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Team team = new Team();

		team.setName("teamA1");

		Player player1 = new Player("playerA1");
		Player player2 = new Player("playerB1");
		
		
		Team team2 = new Team();
		
		team2.setName("teamA2");
		
		Player player3 = new Player("playerA2");
		Player player4 = new Player("playerB2");

		player1.setTeam(team);
		player2.setTeam(team);
		player3.setTeam(team2);
		player4.setTeam(team2);

		session.save(player1);
		session.save(player2);
		session.save(player3);
		session.save(player4);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}
	public static void testGet() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Player player1 =(Player) session.get(Player.class, 1L);
		System.out.println(player1 + "  " + player1.getTeam());
		
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}
	public static void testJoin() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Player player where player.team.name = ?");
		query.setParameter(0, "teamA");
		
		List list = query.list();
		System.out.println(list);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}
	public static void testJoin2() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Player player join player.team where player.team.name like 'team%'");
		
		List list = query.list();
		
	for (Object objs : list) {
		Object[] objects = (Object[])objs;
		System.out.println(objects[0]);
		System.out.println(objects[1]);
		System.out.println("+++++++++++++++++++++++++++++++");
	}
		
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void main(String[] args) {
		testJoin2();
	}

}
