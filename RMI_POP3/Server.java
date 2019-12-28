package RMI_POP3;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public Server() throws RemoteException {

		Registry re = LocateRegistry.createRegistry(2345);
		IRMI i = new MyRMI();
		re.rebind("ok", i);

	}

	public static void main(String[] args) throws RemoteException {
		new Server();
	}
}