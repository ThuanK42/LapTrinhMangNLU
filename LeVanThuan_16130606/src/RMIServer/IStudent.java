package RMIServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStudent extends Remote {

	public abstract String findByName(String name) throws RemoteException;

	public abstract String findByUnderScore(double score) throws RemoteException;

	public abstract String findByUpperScore(double score) throws RemoteException;

	public abstract String findByUnderAge(int age) throws RemoteException;

	public abstract String findByUpperAge(int age) throws RemoteException;

}
