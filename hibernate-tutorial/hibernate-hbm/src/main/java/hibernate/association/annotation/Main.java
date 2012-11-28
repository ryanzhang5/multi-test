package hibernate.association.annotation;

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

		player1.setTeam(team);
		player2.setTeam(team);

		session.save(player1);
		session.save(player2);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void testCreateCompanyAndEmployee() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Company company = new Company();
		company.setName("companyA");

		Company company2 = new Company();
		company2.setName("companyB");

		Employee employee = new Employee();
		employee.setName("employeeA");
		employee.getCompanies().add(company);

		Employee employee2 = new Employee();
		employee2.setName("employeeB");
		employee2.getCompanies().add(company);
		employee2.getCompanies().add(company2);

		Employee employee3 = new Employee();
		employee3.setName("employeeC");
		employee3.getCompanies().add(company);
		employee3.getCompanies().add(company2);

		session.save(employee);
		session.save(employee2);
		session.save(employee3);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void testGetFromTeam() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Team team = (Team) session.get(Team.class, 1L);
		System.out.println(team);
		for (Player player : team.getPlayers()) {
			System.out.println(player);
		}
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void testGetFromPlayer() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Player player = (Player) session.get(Player.class, 1L);

		System.out.println(player + "   " + player.getTeam());
		HibernateUtil.getSessionFactory().close();
	}

	public static void main(String[] args) {
		testCreateCompanyAndEmployee();
	}

}
