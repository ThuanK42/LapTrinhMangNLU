package rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server{
	public static void main(String[] args) throws RemoteException {
		ProductImpl productImpl = new ProductImpl();
		Registry registry = LocateRegistry.createRegistry(12345);
		registry.rebind("Final", productImpl);
	}

}
