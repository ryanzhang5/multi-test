package org.spring.aop.aspectJ.with.parameters;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class NotVeryUsefulAspect {
	/**
	 * PonitCut expression pattern execution(modifiers-pattern? ret-type-pattern
	 * declaring-type-pattern? name-pattern(param-pattern) throws-pattern?) All
	 * parts except the returning type pattern , name pattern, and parameters
	 * pattern are optional.
	 * 
	 * 
	 * The returning type pattern determines what the return type of the method
	 * must be in order for a join point to be matched.
	 * 
	 * Most frequently you will use * as the returning type pattern, which
	 * matches any return type. A fully-qualified type name will match only when
	 * the method returns the given type. The name pattern matches the method
	 * name. You can use the * wildcard as all or part of a name pattern. The
	 * parameters pattern is slightly more complex: () matches a method that
	 * takes no parameters, whereas (..) matches any number of parameters (zero
	 * or more). The pattern (*) matches a method taking one parameter of any
	 * type, (*,String) matches a method taking two parameters, the first can be
	 * of any type, the second must be a String.
	 */
	@Pointcut("execution(public * *(..))")
	public void anyPublicOperation() {
	}

	/*@Pointcut("execution(public * *(..)) && args(name,age)")
	public void anyPublicOperation(String name, int age) {
	}*/
}
