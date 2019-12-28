package server;
 
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server2 {
	public static void main(String[] args) throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(1212);
		IUser iu = new UserDao();
		ISanPham isp = new SanPhamDao();
		registry.rebind("iu", iu);
		registry.rebind("isp", isp);
		System.out.println("server ok");
	}

}
