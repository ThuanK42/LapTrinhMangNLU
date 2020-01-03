import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerAnh {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			IUser iu = new UserDao();
			registry.rebind("iu", iu);
			System.out.println("Server OK");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
