package rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.compute.Compute;

public class ComputePi {
	public static void main(String args[]) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "Compute";
			Registry registry = LocateRegistry.getRegistry("localhost");
			Compute comp = (Compute) registry.lookup(name);
			Pi task = new Pi();
			String pi = comp.executeTask(task);
			System.out.println(pi);
		} catch (Exception e) {
			System.err.println("ComputePi exception:");
			e.printStackTrace();
		}
	}
}