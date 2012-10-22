package org.hibernate.hduo;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.hduo.Player;
import org.hibernate.hduo.Team;

public class NativeApiIllustrationTest extends TestCase {
	private SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@Override
	protected void tearDown() throws Exception {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	public void testBasicUsage() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		/**
		 * <set name="players" cascade="save-update" lazy="true" inverse="true">
		 * <key column="team_id">
		 * </key> <one-to-many
		 * class="org.hibernate.hduo.Player" /> </set>
		 * 
		 * Team team = new Team("team1"); Player p1 = new Player("p1"); Player
		 * p2 = new Player("p2");
		 * 
		 * team.getPlayers().add(p1); team.getPlayers().add(p2);
		 * 
		 * session.save(team);
		 * 
		 * team_id will be null, because player not team will maintain the
		 * relation
		 * 
		 */

		/**
		 * 
		 * <set name="players" cascade="all" lazy="true" inverse="true"> <key
		 * column="team_id"> </key> <one-to-many
		 * class="org.hibernate.hduo.Player" /> </set>
		 * 
		 * <many-to-one name="team" column="team_id"
		 * class="org.hibernate.hduo.Team" lazy="false" cascade="all" />
		 * 
		 * Team team = new Team("team1"); Player p1 = new Player("p1"); Player
		 * p2 = new Player("p2");
		 * 
		 * p1.setTeam(team); p2.setTeam(team);
		 * 
		 * session.save(p1); session.save(p2);
		 * 
		 * this is work because player keeps the relation, of course from
		 * many-to-one side, cascade should be "all"
		 */

		Team team = new Team("team11");
		Player p1 = new Player("p11");
		Player p2 = new Player("p22");
		team.getPlayers().add(p1);
		team.getPlayers().add(p2);
		session.save(team);

		session.getTransaction().commit();
		session.close();
	}
}
