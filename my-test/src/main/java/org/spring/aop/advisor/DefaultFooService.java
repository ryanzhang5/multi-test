package org.spring.aop.advisor;

public class DefaultFooService {
	public String getFoo(String name, int age) {
		System.out.println("this is getFoo(String fooName, int age) " + name
				+ "   " + age);
		return "123456789";
	}

}
