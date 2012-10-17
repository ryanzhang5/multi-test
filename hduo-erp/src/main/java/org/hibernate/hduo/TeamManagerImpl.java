package org.hibernate.hduo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class TeamManagerImpl implements TeamManager {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void addTeam() {
		Student stu = new Student();
		stu.setName("ssssssswwwwwwwwwwww");

		this.sessionFactory.getCurrentSession().save(stu);
		//throw new RuntimeException("ssssssssssssssssssssssssss");
	}

	@Transactional
	public void insertTeam() {

		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		Team team4 = new Team();
		Team team5 = new Team();

		team1.setName("team1a");
		team2.setName("team2a");
		team3.setName("team3a");
		team4.setName("team4a");
		team5.setName("team5a");

		Player p1_1 = new Player();
		Player p1_2 = new Player();
		Player p2_1 = new Player();
		Player p2_2 = new Player();
		Player p3_1 = new Player();
		Player p3_2 = new Player();
		Player p4_1 = new Player();
		Player p4_2 = new Player();
		Player p5_1 = new Player();
		Player p5_2 = new Player();

		p1_1.setName("p1_1");
		p1_2.setName("p1_2");
		p2_1.setName("p2_1");
		p2_2.setName("p2_2");
		p3_1.setName("p3_1");
		p3_2.setName("p3_2");
		p4_1.setName("p4_1");
		p4_2.setName("p4_2");
		p5_1.setName("p5_1");
		p5_2.setName("p5_2");

		p1_1.setTeam(team1);
		p1_2.setTeam(team1);

		p2_1.setTeam(team2);
		p2_2.setTeam(team2);

		p3_1.setTeam(team3);
		p3_2.setTeam(team3);

		p4_1.setTeam(team4);
		p4_2.setTeam(team4);

		p5_1.setTeam(team5);
		p5_2.setTeam(team5);

		this.sessionFactory.getCurrentSession().save(p1_1);
		this.sessionFactory.getCurrentSession().save(p1_2);
		this.sessionFactory.getCurrentSession().save(p2_1);
		this.sessionFactory.getCurrentSession().save(p2_2);
		this.sessionFactory.getCurrentSession().save(p3_1);
		this.sessionFactory.getCurrentSession().save(p3_2);
		this.sessionFactory.getCurrentSession().save(p4_1);
		this.sessionFactory.getCurrentSession().save(p4_2);
		this.sessionFactory.getCurrentSession().save(p5_1);
		this.sessionFactory.getCurrentSession().save(p5_2);

		/*
		 * team1.getPlayers().add(p1_1); team1.getPlayers().add(p1_2);
		 * team2.getPlayers().add(p2_1); team2.getPlayers().add(p2_2);
		 * team3.getPlayers().add(p3_1); team3.getPlayers().add(p3_2);
		 * team4.getPlayers().add(p4_1); team4.getPlayers().add(p4_2);
		 * team5.getPlayers().add(p5_1); team5.getPlayers().add(p5_2);
		 * 
		 * this.sessionFactory.getCurrentSession().save(team1);
		 * this.sessionFactory.getCurrentSession().save(team2);
		 * this.sessionFactory.getCurrentSession().save(team3);
		 * this.sessionFactory.getCurrentSession().save(team4);
		 * this.sessionFactory.getCurrentSession().save(team5);
		 */

	}

	@Transactional
	public void selectTeams() {

		List<Team> teams = (List<Team>) this.sessionFactory.getCurrentSession()
				.getNamedQuery("team.selectAll").list();
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=");
		for (Team team : teams) {
			System.out.println(team.getName() + "------->"
					+ Arrays.toString(team.getPlayers().toArray()));
		}
	}

}
