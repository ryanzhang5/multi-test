package org.spring.aop.introduction;

import org.spring.aop.MethodInvocation;

public class LockMixin extends DelegatingIntroductionInterceptor implements
		Lockable {
	private boolean locked;

	public void lock() {
		this.locked = true;
	}

	public void unlock() {
		this.locked = false;
	}

	public boolean locked() {
		return this.locked;
	}

	public Object invoke(MethodInvocation invocation) throws Exception {
		if (locked() && invocation.getMethod().getName().indexOf("foo") == 0) {
			System.out.println("you can not invoke this method");
		}

		return super.invoke(invocation);
	}
}
