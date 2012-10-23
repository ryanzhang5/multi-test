package org.hibernate.hduo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hduo.manager.ClientManager;
import com.hduo.manager.IncomeItemManager;
import com.hduo.pojo.Client;

public class HibernateTest {

	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		 IncomeItemManager incomeItemManager = (IncomeItemManager) ctx.getBean("incomeItemManager2");

		 
	
	}

}
