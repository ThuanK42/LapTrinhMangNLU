package RMIServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server1 {
	public Server1() throws RemoteException {
		Registry regis = LocateRegistry.createRegistry(12345);
		IStudent sv = new StudentImp();
		regis.rebind("sv", sv);
	}
	
	public static void main(String[] args) throws RemoteException {
		new Server1();
	}

}
