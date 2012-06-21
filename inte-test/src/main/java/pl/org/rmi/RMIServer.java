package pl.org.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
	public static void main(String [] args)throws RemoteException,MalformedURLException, InterruptedException{
		LocateRegistry.createRegistry(1099);
		Hello hello = new HelloImpl();
		Naming.rebind("server.hello", hello);
		System.out.println("server RMI server is ready");
		//Thread.sleep(30*1000);
	}
}
