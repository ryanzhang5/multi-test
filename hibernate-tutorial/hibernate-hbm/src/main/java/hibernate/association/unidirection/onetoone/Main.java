package hibernate.association.unidirection.onetoone;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;

public class Main {
	public static void testCreate() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Person person1 = new Person();
		person1.setName("person1");
		Person person2 = new Person();
		person2.setName("person2");
		Address address = new Address();
		address.setStreet("wall street");
		Address address2 = new Address();
		address2.setStreet("newyork");
		
		person1.setAddress(address);
		person2.setAddress(address2);
		
		session.save(person1);
		session.save(person2);
		
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}
	public static void testGet() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void main(String[] args) {
		testCreate();
	}

}
