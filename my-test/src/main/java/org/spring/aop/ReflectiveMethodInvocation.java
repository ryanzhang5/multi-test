package org.spring.aop;

import java.lang.reflect.Method;
import java.util.List;

public class ReflectiveMethodInvocation implements MethodInvocation {

	private Object target;

	private Object[] args;

	private Method method;

	List<MethodInterceptor> interceptors = null;

	int index = 0;

	public ReflectiveMethodInvocation(Object target, Method method,
			Object[] args, List<MethodInterceptor> interceptors) {
		this.target = target;
		this.method = method;
		this.args = args;
		this.interceptors = interceptors;
	}

	public Object proceed() throws Exception {

		if (index == interceptors.size()) {
			return method.invoke(target, args);
		} else {
			MethodInterceptor interceptor = interceptors.get(index);
			index++;
			 return interceptor.invoke(this);
		}

	}

	public Method getMethod() {
		return this.method;
	}

	public Object[] getArguments() {
		return this.args;
	}

	public Object getThis() {
		return this.target;
	}

}
