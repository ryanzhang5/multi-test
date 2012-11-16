package org.spring.aop.aspectJ.no.parameters;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterExample {
	private static final Logger logger = Logger.getLogger(AfterExample.class);
	@AfterReturning("execution(public * *(..)))")
	private void after() {
		logger.info("this is after without return value");
	}
}
