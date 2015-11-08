package com.ms.cxfserver;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class Client2 {
public static void main(String[] args) {
	//创建web服务代理工厂  
	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
	//设置要调用的web服务服务端发布地址  
	factory.setAddress("http://localhost:4444/server/order");  
	//设置要调用的web服务  
	factory.setServiceClass(OrderProcess.class);  
	//创建web服务对象  
	OrderProcess orderProcess = (OrderProcess) factory.create();  	
	System.out.println(orderProcess.processOrder(null));
}
}
