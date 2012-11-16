package org.spring.aop.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class AroundExample implements MethodInterceptor {
	private static final Logger logger = Logger.getLogger(AroundExample.class);

	public Object invoke(MethodInvocation invocation) throws Throwable {
		logger.info("Before: invocation=[" + invocation + "]");
		Object rval = invocation.proceed();
		logger.info("Invocation returned");
		return rval;
	}
}