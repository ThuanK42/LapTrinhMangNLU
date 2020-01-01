package exercise27;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDownload extends Remote{
	int openFile(String sf) throws RemoteException;
	byte[] readData(int id) throws RemoteException;
	void closeFile(int id) throws RemoteException;
}
