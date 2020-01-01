package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(1212);

		IUser iu = new UserDAO();
		ISanPham isp = new SanPhamDao();

		registry.rebind("iu", iu);
		registry.rebind("isp", isp);

		System.out.println("Server OK");

	}

}
