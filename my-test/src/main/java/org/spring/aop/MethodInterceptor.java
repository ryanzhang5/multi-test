package org.spring.aop;

public interface MethodInterceptor {

	public  Object invoke(MethodInvocation mi) throws Exception;

}