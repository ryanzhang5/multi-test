package com.ms.cxfserver;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class OrderServer2 {
	public static void main(String[] args) {
		JaxWsServerFactoryBean jwsFactory = new JaxWsServerFactoryBean();
		jwsFactory.setAddress("http://localhost:8080/server"); // 指定WebService的发布地址
		jwsFactory.setServiceClass(OrderProcess.class);// WebService对应的类型
		jwsFactory.setServiceBean(new OrderProcessImpl());// WebService对应的实现对象
		jwsFactory.create();
	}
}
