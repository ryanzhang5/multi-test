package org.spring.dao.tx.declarative;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {

		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				new String[] { "/org/spring/dao/tx/declarative/applicationContext.xml" });

		EmployeeDao employeeDao = (EmployeeDao) beanFactory
				.getBean("employeeDao");

		System.out.println(employeeDao.countAll());
		System.out.println(employeeDao.countAllWithPapameter());
		System.out.println(employeeDao.getNameById());
		System.out.println(employeeDao.getEmployeeById());
		System.out.println(employeeDao.getAllEmployees());
		//employeeDao.addEmployee();
		//employeeDao.deleteEmployee();
		//employeeDao.updateEmployee();

	}

}
