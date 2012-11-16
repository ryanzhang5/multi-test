package org.spring.aop.aspectJ.no.parameters;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AroundExample {
	private static final Logger logger = Logger.getLogger(AroundExample.class);

	/**
	 * Around advice is declared using the @Around annotation. The first
	 * parameter of the advice method must be of type ProceedingJoinPoint.
	 */

	@Around("execution(public * *(..)))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("before around");
		Object retVal = pjp.proceed();
		logger.info("after around");
		return retVal;
	}
}
