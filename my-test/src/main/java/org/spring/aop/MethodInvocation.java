package org.spring.aop;

import java.lang.reflect.Method;

public interface MethodInvocation {
	Object proceed() throws Exception;

	public Method getMethod();

	public Object[] getArguments();

	public Object getThis();
}
