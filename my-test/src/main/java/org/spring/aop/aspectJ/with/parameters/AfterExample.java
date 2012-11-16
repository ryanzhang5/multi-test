package org.spring.aop.aspectJ.with.parameters;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterExample {
	private static final Logger logger = Logger.getLogger(AfterExample.class);
	@AfterReturning(pointcut = "execution(public * *(..))", returning = "retVal")
	private void afterWithReturnValue(Object retVal) {
		logger.info("this is after with return value "
				+ retVal.toString());
	}
}
