package org.spring.aop.aspectJ.no.parameters;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BeforeExample {
	private static final Logger logger = Logger.getLogger(BeforeExample.class);

	// a simple reference to a named pointcut
	// aspectj and aspectjweaver 1.5 cause error at ::0 can't find referenced
	// pointcut anyPublicOperation,but 1.7 version will works
	@Before("org.spring.aop.aspectJ.no.parameters.NotVeryUsefulAspect.anyPublicOperation()")
	public void before() {
		logger.info("this is before");
	}

}
