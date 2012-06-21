package pl.org.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {

	public HelloImpl() throws RemoteException {
	}

	public String greeting() throws RemoteException {

		return "greeting";
	}

}
