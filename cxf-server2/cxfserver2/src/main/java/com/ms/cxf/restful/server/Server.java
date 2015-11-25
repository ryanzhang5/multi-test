package com.ms.cxf.restful.server;

import java.util.ArrayList;
import java.util.List;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

public class Server {
	public static void main(String[] args) {
		RoomService service = new RoomService();
		List<Object> services = new ArrayList<Object>();
		services.add(service);
		services.add(new JsonService());
		// Service instance
		JAXRSServerFactoryBean restServer = new JAXRSServerFactoryBean();
		restServer.setResourceClasses(Room.class, Person.class);
		restServer.setServiceBeans(services);
		restServer.setAddress("http://localhost:9999/");
		restServer.create();
	}
}
