package rmi.ud;

import java.rmi.*;

public interface IFile extends Remote{
	// login pop3
	boolean checkUserName(String name) throws RemoteException;
	boolean checkPass(String pass) throws RemoteException;
	
	// upload file
	void createDest(String df) throws RemoteException;
	void writeData(byte[] buff, int lenght) throws RemoteException;
	void closeDest() throws RemoteException;
	
	// download file
	void openSource(String sf) throws RemoteException;
	byte[] readData() throws RemoteException;
	void closeSource() throws RemoteException;

}
