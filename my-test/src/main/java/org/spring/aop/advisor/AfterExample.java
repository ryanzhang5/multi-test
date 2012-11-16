package org.spring.aop.advisor;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

public class AfterExample implements AfterReturningAdvice {
	private static final Logger logger = Logger.getLogger(AfterExample.class);

	public void afterReturning(Object returnValue, Method m, Object[] args,
			Object target) throws Throwable {
		logger.info(m.getName() + "  " + Arrays.toString(args) + "  " + target);
	}
}
