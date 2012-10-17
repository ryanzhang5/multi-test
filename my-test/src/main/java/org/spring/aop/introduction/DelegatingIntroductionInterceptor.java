package org.spring.aop.introduction;

import java.util.LinkedHashSet;
import java.util.Set;

import org.spring.aop.MethodInterceptor;
import org.spring.aop.MethodInvocation;

public class DelegatingIntroductionInterceptor implements MethodInterceptor {

	private Set<Class> publishedInterfaces;

	private Object delegate;

	public DelegatingIntroductionInterceptor() {
		delegate = this;

		Class<?> clazz = this.getClass();

		Set<Class> interfaces = new LinkedHashSet<Class>();

		Class<?>[] ifcs = clazz.getInterfaces();
		for (Class<?> ifc : ifcs) {
			interfaces.add(ifc);
		}

		this.publishedInterfaces = interfaces;
	}

	public Object invoke(MethodInvocation mi) throws Exception {
		Object obj = null;
		if (implementsInterface(mi.getMethod().getDeclaringClass())) {
			obj = mi.getMethod().invoke(delegate, mi.getArguments());
		} else {
			obj = mi.proceed();
		}
		return obj;
	}

	public boolean implementsInterface(Class ifc) {
		for (Class pubIfc : this.publishedInterfaces) {
			if (ifc.isInterface() && ifc.isAssignableFrom(pubIfc)) {
				return true;
			}
		}
		return false;
	}

}
