package com.ms.cxfserver;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class OrderServer {
	public static void main(String[] args) {
		// 创建web服务工厂
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		// 设置服务类
		factory.setServiceClass(OrderProcess.class);
		// 设置对外发布服务地址
		factory.setAddress("http://localhost:4444/server");
		factory.setServiceBean(new OrderProcessImpl());
		// 创建服务
		Server server = factory.create();
		// 启动服务
		server.start();
	}
}
