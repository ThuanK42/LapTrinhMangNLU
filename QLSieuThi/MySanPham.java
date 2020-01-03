package manSERVER2014_15;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MySanPham  extends Remote{

	public boolean checkExistUserName(String username) throws RemoteException;
	public boolean login(String username,String pass) throws RemoteException;
	public User get(String username) throws RemoteException;
	
	
}