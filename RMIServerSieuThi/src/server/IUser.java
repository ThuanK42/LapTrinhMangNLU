package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUser extends Remote {

	public abstract boolean checkUser(String name)throws RemoteException;;

	public abstract int checkLogin(String password)throws RemoteException;;
}
