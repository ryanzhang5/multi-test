package rmi.engine;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmi.compute.Compute;

public class RegistryTest {

	public static void main(String[] args) throws Exception{
		String name = "Compute";
		Compute engine = new ComputeEngine();
		Compute stub = (Compute) UnicastRemoteObject
				.exportObject(engine, 0);
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind(name, stub);
		System.out.println(registry);
		   System.in.read();
	}

}
