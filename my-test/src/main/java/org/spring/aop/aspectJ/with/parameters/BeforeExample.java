package org.spring.aop.aspectJ.with.parameters;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BeforeExample {
	private static final Logger logger = Logger.getLogger(BeforeExample.class);

	/**
	 * Any advice method may declare as its first parameter, a parameter of type
	 * org.aspectj.lang.JoinPoint (please note that around advice is required to
	 * declare a first parameter of type ProceedingJoinPoint, which is a
	 * subclass of JoinPoint.
	 */
	@Before("org.spring.aop.aspectJ.with.parameters.NotVeryUsefulAspect.anyPublicOperation()")
	public void before(JoinPoint joinPoint) {
		logger.info("before(JoinPoint joinPoint)  "
				+ joinPoint.getTarget().getClass().getName());
	}

	/**
	 * see github about how to use argNames
	 */
	@Before(value = "org.spring.aop.aspectJ.with.parameters.NotVeryUsefulAspect.anyPublicOperation() && args(n,a)", argNames = "a,n")
	public void before(int age, String userName) {
		logger.info("this is before(String age,int userName)  " + userName
				+ " " + age);
	}

	@Before(value = "execution(public * *(..)) && args(n,a)", argNames = "a,n")
	public void before2(int age, String userName) {
		logger.info("this is before2(String age,int userName)  " + userName
				+ " " + age);
	}

	@Before(value = "execution(public * *(..)) && args(userName,age)")
	public void before3(int age, String userName) {
		logger.info("this is before3(String age,int userName)  " + userName
				+ " " + age);
	}

}
