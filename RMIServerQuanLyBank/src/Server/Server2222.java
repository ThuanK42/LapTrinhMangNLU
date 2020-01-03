package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server2222 {
	public static void main(String[] args) throws RemoteException {

		Registry reg = LocateRegistry.createRegistry(12345);
		IBank iBank = new Dao();
		reg.rebind("iBank", iBank);
		System.out.println("OK Server");

	}
}
