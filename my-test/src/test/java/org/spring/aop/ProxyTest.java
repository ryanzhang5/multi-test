package org.spring.aop;

import java.lang.reflect.Method;

import org.spring.aop.introduction.LockMixin;
import org.spring.aop.introduction.Lockable;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProxyTest {

	public ProxyFactory factory = null;

	@BeforeClass
	public void init() {
		System.out.println("only once");
		factory = new ProxyFactory();
		factory.setTarget(new SimplePojo());

	}

	@BeforeMethod
	public void init2() {
		System.out.println("before every method, so many times");
	}

	@Test
	public void beforeTest() {

		Pojo pojo = (Pojo) factory.getProxy(new Class<?>[] { Pojo.class });

		factory.addMethodBeforeAdvice(new MethodBeforeAdvice() {

			public void before(Method method, Object[] args, Object target) {
				System.out.println("1111111111111111111111");

			}

		});

		factory.addMethodBeforeAdvice(new MethodBeforeAdvice() {

			public void before(Method method, Object[] args, Object target) {
				System.out.println("2222222222222222222");

			}

		});

		pojo.foo();
	}

	@Test
	public void afterTest() {
		Pojo pojo = (Pojo) factory.getProxy(new Class<?>[] { Pojo.class });

		factory.addAfterReturnAdvice(new AfterReturningAdvice() {
			public void afterReturning(Object returnValue, Method method,
					Object[] args, Object target) {
				System.out.println("1111111111111111111111111");
			}
		});

		factory.addAfterReturnAdvice(new AfterReturningAdvice() {
			public void afterReturning(Object returnValue, Method method,
					Object[] args, Object target) {
				System.out.println("2222222222222222222222222");
			}
		});

		pojo.foo();
	}

	@Test
	public void aroundTest() {
		Pojo pojo = (Pojo) factory.getProxy(new Class<?>[] { Pojo.class });

		factory.addMethodInterceptor(new MethodInterceptor() {

			public Object invoke(MethodInvocation mi) throws Exception {
				System.out.println("111111111111111111111111111");
				Object obj = mi.proceed();
				System.out.println("2222222222222222222222222");
				return obj;

			}

		});
		factory.addMethodInterceptor(new MethodInterceptor() {

			public Object invoke(MethodInvocation mi) throws Exception {
				System.out.println("33333333333333333333333");
				Object obj = mi.proceed();
				System.out.println("444444444444444444444444444444");
				return obj;
			}

		});
		pojo.foo();
	}

	@Test
	public void introductionTest() {
		factory.addInterface(Lockable.class);
		factory.addInterface(Pojo.class);
		factory.addMethodInterceptor(new LockMixin());

		Object proxy = factory.getProxy();

		Lockable lock = (Lockable) proxy;

		lock.lock();

		Pojo pojo = (Pojo) proxy;

		pojo.foo();

	}
}
