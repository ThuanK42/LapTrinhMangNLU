package exercise28;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUpload extends Remote {
	int createDestFile(String df) throws RemoteException;
	void writeFile(byte[] buff, int length, int id) throws RemoteException;
	void closeDestFile(int id) throws RemoteException;
}
