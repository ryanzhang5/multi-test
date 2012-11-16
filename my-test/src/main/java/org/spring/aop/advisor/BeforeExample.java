package org.spring.aop.advisor;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * A simpler advice type is a before advice. This does not need a
 * MethodInvocation object, since it will only be called before entering the
 * method. The main advantage of a before advice is that there is no need to
 * invoke the proceed() method, and therefore no possibility of inadvertently
 * failing to proceed down the interceptor chain.
 * 
 */
public class BeforeExample implements MethodBeforeAdvice {
	private static final Logger logger = Logger.getLogger(BeforeExample.class);

	public void before(Method m, Object[] args, Object target) throws Throwable {
		logger.info(m.getName() + "  " + Arrays.toString(args) + "  " + target);
	}

}
