package org.spring.aop.advisor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {

		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				new String[] { "/org/spring/aop/advisor/coreservletsAopContext.xml" });

		DefaultFooService query = (DefaultFooService) beanFactory
				.getBean("fooService");

		query.getFoo("hari", 23);
	}

}
