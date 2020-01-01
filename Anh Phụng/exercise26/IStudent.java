package exercise26;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStudent extends Remote {
	public String findByName(String name) throws RemoteException;
	public String findByAge(double age) throws RemoteException;
	public String findByLessScore(double score) throws RemoteException;
}
