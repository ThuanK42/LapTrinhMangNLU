package rmi.ud;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException  {
		FileImpl file = new FileImpl();
		Registry registry = LocateRegistry.createRegistry(12345);
		registry.rebind("File", file);
	}
}
