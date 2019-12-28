package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUser extends Remote {

	public boolean checkUser(String name) throws RemoteException;

	public int checkLogin(String pass) throws RemoteException;

}
