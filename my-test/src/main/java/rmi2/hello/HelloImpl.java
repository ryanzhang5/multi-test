package rmi2.hello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {

	protected HelloImpl() throws RemoteException {
		super();
	}

	public String greeting() throws RemoteException {
		return "this is my greeting";
	}

}
