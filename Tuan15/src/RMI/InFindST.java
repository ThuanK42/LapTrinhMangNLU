package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
//import java.rmi.RemoteException;
//doc dang file nhi phan hoac file txt
public interface InFindST extends Remote {
	public abstract  String findByLessName(String name)  throws RemoteException;
    public abstract String findByLessAge(int age) throws RemoteException;
    public abstract String findByLessScore(double score) throws RemoteException;
    
}
