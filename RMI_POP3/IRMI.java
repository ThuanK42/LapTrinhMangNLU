package RMI_POP3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMI extends Remote {
	public boolean checkExistUserName(String username) throws RemoteException;

	public boolean login(String username, String pass) throws RemoteException;

	public User get(String usename) throws RemoteException;

	public void createDest(String s) throws RemoteException;

	public void writeData(byte[] b, int lenght) throws RemoteException;

	public void closeDest() throws RemoteException;

	public void openSource(String s) throws RemoteException;

	public byte[] readData() throws RemoteException;

	public void closeSource() throws RemoteException;
}