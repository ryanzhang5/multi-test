package org.spring.aop.aspectJ.with.parameters;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AroundExample {
	private static final Logger logger = Logger.getLogger(AroundExample.class);
	@Around(value = "execution(public * *(..)) && args(n,a)", argNames = "n,a")
	public Object around(ProceedingJoinPoint pjp, String name, int age)
			throws Throwable {
		logger.info("before around " + name + "  " + age);
		Object retVal = pjp.proceed();
		logger.info("after around" + name + "  " + age);
		return retVal;
	}
}
