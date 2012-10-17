package org.spring.aop;

public class AfterReturningAdviceInterceptor implements MethodInterceptor{
	private AfterReturningAdvice advice;

	public AfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
		this.advice = advice;
	}

	public Object invoke(MethodInvocation mi) throws Exception {

		Object retval = mi.proceed();
		this.advice.afterReturning(retval, mi.getMethod(), mi.getArguments(),
				mi.getThis());
		return retval;
	}
}
