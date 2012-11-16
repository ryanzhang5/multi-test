package org.spring.aop.schema.based.no.parameters;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class AspectExample {
	private static final Logger logger = Logger.getLogger(AspectExample.class);

	public void before1() {
		logger.info("this is before1");
	}

	public void before2() {
		logger.info("this is before2");
	}

	public void afterReturn1() {
		logger.info("this is afterReturn1");
	}

	public void afterReturn2(Object retVal) {
		logger.info("this is afterReturn2 " + retVal);
	}

	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("this is before around ");
		Object retVal = pjp.proceed();
		logger.info("this is after around ");
		return retVal;
	}}
