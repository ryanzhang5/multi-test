package org.spring.aop;

public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
	private MethodBeforeAdvice advice;

	public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
		this.advice = advice;
	}

	public Object invoke(MethodInvocation mi) throws Exception {
		this.advice.before(mi.getMethod(), mi.getArguments(), mi.getThis());
		return mi.proceed();
	}
}
