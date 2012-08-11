package org.hibernate.hduo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateTest {

	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		 TeamManager teamManager = (TeamManager) ctx.getBean("teamManager");

		 teamManager.addTeam();
			
			
	}

}
