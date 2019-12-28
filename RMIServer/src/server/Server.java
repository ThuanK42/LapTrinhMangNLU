package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException {
		Registry r = LocateRegistry.createRegistry(1234); // port
		IUser iu = new UserDAO();
		ISinhVien isv = new SVDAO();
		r.rebind("iu", iu);
		r.rebind("isv", isv);
		System.out.println("server ok");
	}
}
