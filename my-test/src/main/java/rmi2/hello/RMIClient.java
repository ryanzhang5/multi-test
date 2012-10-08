package rmi2.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
	public static void main(String[] args)throws Exception {
		Registry registry = LocateRegistry.getRegistry("localhost");
		Hello hello = (Hello)registry.lookup("server.hello");
		System.out.println(hello.greeting());
		
	}
}
