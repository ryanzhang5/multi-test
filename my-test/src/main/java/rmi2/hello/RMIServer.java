package rmi2.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

	
	public static void main(String[] args) throws Exception{
		Registry registry = LocateRegistry.createRegistry(1099);
		Hello hello = new HelloImpl();
		registry.rebind("server.hello", hello);
		
	}

}
