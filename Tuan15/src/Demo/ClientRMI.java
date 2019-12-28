package Demo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
	static int port = 1099;
	static String ip = "127.0.0.1";

	public static void main(String[] args) throws RemoteException, NotBoundException {
		// String ok = "User can find method......:"+"\n"+;
		String ok = "User can find method,such as:" + "\n" + "1.findByName";
		Registry registry = LocateRegistry.getRegistry(ip, port);
		registry.lookup("/InFindST");
	}
}
