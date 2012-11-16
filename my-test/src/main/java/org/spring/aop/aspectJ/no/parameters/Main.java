package org.spring.aop.aspectJ.no.parameters;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {

		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				new String[] { "/org/spring/aop/aspectJ/no/parameters/coreservletsAopContext.xml" });

		DefaultFooService query = (DefaultFooService) beanFactory
				.getBean("fooService");

		query.getFoo();
	}

}
