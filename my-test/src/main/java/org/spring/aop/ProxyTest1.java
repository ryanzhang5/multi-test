package org.spring.aop;

import java.lang.reflect.Method;

public class ProxyTest1 {
	public static void main(String[] args) {
		
		ProxyFactory fac = new ProxyFactory();

		fac.setTarget(new SimplePojo());
		fac.addMethodBeforeAdvice(new MethodBeforeAdvice() {

			public void before(Method method, Object[] args, Object target) {
				System.out.println("1111111111111111111111");

			}

		});

		fac.addMethodBeforeAdvice(new MethodBeforeAdvice() {

			public void before(Method method, Object[] args, Object target) {
				System.out.println("2222222222222222222");

			}

		});
		Pojo pojo = (Pojo) fac.getProxy(new Class<?>[] { Pojo.class });

		pojo.foo();
	}

}
