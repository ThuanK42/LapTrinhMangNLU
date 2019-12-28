package manSERVER2014_15;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
public Server() throws RemoteException {
	Registry re=LocateRegistry.createRegistry(1221);
	MySanPham my=new SanPhamDAOImpt();
	re.rebind("sp", my);
	
}
public static void main(String[] args) throws RemoteException {
	new Server();
}
}