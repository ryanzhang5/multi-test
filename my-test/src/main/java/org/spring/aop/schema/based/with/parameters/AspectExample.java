package org.spring.aop.schema.based.with.parameters;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AspectExample {
	private static final Logger logger = Logger.getLogger(AspectExample.class);

	public void before1(String name, int age) {
		logger.info("this is before1 " + name + " " + age);
	}

	public void before2(String name, int age) {
		logger.info("this is before2 " + name + " " + age);
	}

	public void afterReturn1() {
		logger.info("this is afterReturn1");
	}

	public void afterReturn2(JoinPoint joinPoint ,Object retVal,String name,int age) {
		logger.info("this is afterReturn2 " +joinPoint + "  " +  retVal + " " + name+ "   " + age);
	}

	public Object around(ProceedingJoinPoint pjp, String name, int age)
			throws Throwable {
		logger.info("this is before around " + " " + name + " " + age);
		Object retVal = pjp.proceed();
		logger.info("this is after around " + " " + name + " " + age);
		return retVal;
	}
}
