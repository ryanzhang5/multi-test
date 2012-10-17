package rmi2.hello;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote{
	
	String greeting()throws RemoteException;
}
