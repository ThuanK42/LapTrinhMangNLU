package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienrRMI {
	static int port = 1099;
	static String ip="127.0.0.1";
public static void main(String[] args)throws RemoteException {
	String ok="User can find method,such as:"+"\n"+"1.findByName";
	
	Registry registry=LocateRegistry.getRegistry(ip,port);
	InFindStudent  lookup=(InFindStudent) registry.lookup("/InFindStudent");
	System.out.println(ok);
	
}
}
