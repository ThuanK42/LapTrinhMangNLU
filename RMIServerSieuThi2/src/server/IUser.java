package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUser extends Remote{
	
	public int checkUser(String name) throws RemoteException;
	
	public int checkLogin(String pass) throws RemoteException;

}
