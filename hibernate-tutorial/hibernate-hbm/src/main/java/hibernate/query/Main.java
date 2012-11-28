package hibernate.query;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import courses.hibernate.util.HibernateUtil;

public class Main {
	public static void testCreate() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		for (int i = 0; i < 50; i++) {
			Client client1 = new Client("aaa" + i, i);
			session.save(client1);
		}

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Client.class);
		List clientList = criteria.list();
		System.out.println("------------------" + clientList);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void getAllOrder() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Client.class).addOrder(
				Order.desc("age"));
		List clientList = criteria.list();
		System.out.println("------------------" + clientList);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void getSingle() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Client.class);

		Criterion criterion = Restrictions.eq("name", "bbb");

		criteria.add(criterion);

		Object obj = criteria.uniqueResult();
		System.out.println(obj);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void getBetween() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Client.class);

		criteria.add(Restrictions.between("age", 30, 50));
		List clientList = criteria.list();
		System.out.println("------------------" + clientList);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void getIn() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Client.class);
		Integer[] ages = new Integer[] { 30, 40, 50 };
		criteria.add(Restrictions.in("age", ages));
		List clientList = criteria.list();
		System.out.println("------------------" + clientList);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void getStringMatching() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.like("name", "a%").ignoreCase());
		List clientList = criteria.list();
		System.out.println("------------------" + clientList);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void and() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.eq("name", "aaa"));
		criteria.add(Restrictions.eq("age", 10));
		List clientList = criteria.list();
		System.out.println("------------------" + clientList);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void or() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.eq("name", "aaa"));
		criteria.add(Restrictions.or(Restrictions.eq("age", 10),
				Restrictions.eq("age", 20)));
		List clientList = criteria.list();
		System.out.println("------------------" + clientList);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void example() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Client client = new Client();
		client.setName("aaa");

		Example example = Example.create(client);
		example.excludeZeroes();

		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(example);
		List clientList = criteria.list();
		System.out.println("------------------" + clientList);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void hql1() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("from Client");

		List clientList = query.list();
		System.out.println("------------------" + clientList);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void pagination() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		int start = 0;
		int size = 10;

		Query query = null;
		List clientList = null;
		do {
			query = session.createQuery("from Client").setMaxResults(size)
					.setFirstResult(start);
			clientList = query.list();
			System.out.println("------------------from" + start + " to "
					+ (start + size));
			for (Object object : clientList) {
				System.out.println("++++++++++++++===============" + object);
			}
			start = clientList.size() + start;
		} while (clientList != null && clientList.size() > 0);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void getAllClients() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = null;
		List clientList = null;
		query = session.getNamedQuery("getAllClients");
		clientList = query.list();
		for (Object object : clientList) {
			System.out.println("++++++++++++++===============" + object);
		}
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void getClientsByAge() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = null;
		List clientList = null;
		query = session.getNamedQuery(
				"hibernate.query.Client.getClientsByAge").setParameter(
				"clientAge", 30);
		clientList = query.list();
		for (Object object : clientList) {
			System.out.println("++++++++++++++===============" + object);
		}
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void main(String[] args) {
		getClientsByAge();
	}

}
