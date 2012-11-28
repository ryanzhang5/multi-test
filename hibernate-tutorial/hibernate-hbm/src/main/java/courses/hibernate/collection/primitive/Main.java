package courses.hibernate.collection.primitive;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;

public class Main {
	public static void testCreateUser() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		User user = new User();
		
		user.getAddressSet().add("addressA");
		user.getAddressSet().add("addressB");
		user.getAddressSet().add("addressC");

		user.getAddressList().add("addressB");
		user.getAddressList().add("addressC");
		user.getAddressList().add("addressA");
		
		
		user.getAddressMap().put(1, "mapA");
		user.getAddressMap().put(2, "mapB");
		user.getAddressMap().put(3, "mapC");
		session.saveOrUpdate(user);
		
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}
	public static void testGetUser() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		User user = (User) session.get(User.class, 1L);
		System.out.println(user);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void main(String[] args) {
		//testCreateUser();
		testGetUser();
	}

}
