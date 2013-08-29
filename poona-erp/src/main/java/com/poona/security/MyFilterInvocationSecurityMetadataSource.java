package com.poona.security;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class MyFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		return null;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		   return FilterInvocation.class.isAssignableFrom(clazz);  
	}

}
