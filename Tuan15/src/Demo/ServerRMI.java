package Demo;

import java.nio.channels.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {
	static int port= 1099;
	
public static void main(String[] args) throws RemoteException, java.rmi.AlreadyBoundException{
	Registry registry = LocateRegistry.createRegistry(port);
	System.out.println("Waiting............");
	registry.bind("/InFindST", new RMIProcessST());
	System.out.println("Accepted...........");
	
}
}
