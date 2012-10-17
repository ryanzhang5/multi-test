package rmi.engine;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmi.compute.Compute;
import rmi.compute.Task;

public class ComputeEngine implements Compute {

	public ComputeEngine() {
		super();
	}

	public <T> T executeTask(Task<T> t) {
		return t.execute();
	}

	
	 //java -cp /home/ryan/jone/public_html/classes/computer.jar:/home/ryan/jone/src  -Djava.rmi.server.codebase=file://localhost/home/ryan/jone/public_html/classes/  -Djava.security.policy=client.policy rmi.client.ComputePi
	 //java -cp /home/ryan/ann/public_html/classes/computer.jar:/home/ryan/ann/src  -Djava.rmi.server.codebase=file://localhost/home/ryan/ann/public_html/classes/ -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy rmi.engine.ComputeEngine
	
	
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "Compute";
			Compute engine = new ComputeEngine();
			Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
			//Registry registry = LocateRegistry.getRegistry();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind(name, stub);
			System.out.println("ComputeEngine bound");
		} catch (Exception e) {
			System.err.println("ComputeEngine exception:");
			e.printStackTrace();
		}
	}
}