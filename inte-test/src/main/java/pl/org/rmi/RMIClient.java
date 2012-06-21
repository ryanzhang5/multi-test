package pl.org.rmi;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

	public static void main(String[] args)throws  RemoteException,MalformedURLException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("localhost");
		Hello hello = (Hello)registry.lookup("server.hello");
		System.out.println(hello.greeting());
	}

}
