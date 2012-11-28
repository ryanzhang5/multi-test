package hibernate.association.unidirection.manytomany;

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
		Person person3 = new Person();
		person3.setName("person3");
		Address address = new Address();
		address.setStreet("wall street");
		Address address2 = new Address();
		address2.setStreet("newyork");

		person1.getAddresses().add(address);
		person1.getAddresses().add(address2);

		
		person2.getAddresses().add(address);
		person2.getAddresses().add(address2);

		person3.getAddresses().add(address);
		person3.getAddresses().add(address2);
		
		
		session.save(person1);
		session.save(person2);
		session.save(person3);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void main(String[] args) {
		testCreate();
	}

}
