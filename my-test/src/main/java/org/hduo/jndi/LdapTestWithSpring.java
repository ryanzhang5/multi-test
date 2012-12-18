package org.hduo.jndi;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LdapTestWithSpring {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"org/hduo/jndi/applicationContext.xml");
		UserDaoImpl userDao = (UserDaoImpl) ctx.getBean("userDao");
		List userList = userDao.getAllContactNames();
		System.out.println("------------------------------ "+userList.get(0));
	}

}
