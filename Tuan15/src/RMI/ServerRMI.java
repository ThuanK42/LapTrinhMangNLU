package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {
static int port=1033;
public static void main(String[] args)  throws RemoteException{
	Registry registry = LocateRegistry.createRegistry(port);
	System.out.println("Wating...........");
	registry.bind("/InfindStudent", new RMIProcessStudent());
 System.out.println("Acception............");
}
}
