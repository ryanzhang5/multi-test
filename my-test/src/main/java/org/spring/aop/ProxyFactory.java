package org.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxyFactory implements InvocationHandler {

	private Object target;

	private List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();

	private List<Class<?>> interfaces = new ArrayList<Class<?>>();

	public void setTarget(Object target) {
		this.target = target;
	}

	public void addMethodBeforeAdvice(MethodBeforeAdvice advice) {
		this.interceptors.add(new MethodBeforeAdviceInterceptor(advice));
	}

	public void addAfterReturnAdvice(AfterReturningAdvice advice) {
		this.interceptors.add(new AfterReturningAdviceInterceptor(advice));
	}

	public void addMethodInterceptor(MethodInterceptor methodInterceptor) {
		this.interceptors.add(methodInterceptor);
	}

	public void addInterface(Class<?> interfac) {
		this.interfaces.add(interfac);
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		MethodInvocation mi = new ReflectiveMethodInvocation(target, method,
				args, interceptors);
		return mi.proceed();
	}

	public Object getProxy() {
		Class<?>[] clazz = new Class<?>[interfaces.size()];

		for (int i = 0; i < clazz.length; i++) {
			clazz[i] = interfaces.get(i);
		}

		return getProxy(clazz);
	}

	public Object getProxy(Class<?>[] interfaces) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				interfaces, this);
	}

}
