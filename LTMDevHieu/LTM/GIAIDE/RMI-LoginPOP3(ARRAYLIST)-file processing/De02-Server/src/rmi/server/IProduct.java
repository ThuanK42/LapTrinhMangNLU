package rmi.server;
import java.io.File;
import java.rmi.*;
import java.util.ArrayList;

public interface IProduct extends Remote {
	String getWelcomMessage() throws RemoteException;
	void saveDataFile(File file) throws RemoteException;
	void saveDataString(String chuoi) throws RemoteException;
	
	int getSum() throws RemoteException;
	int getNums() throws RemoteException;
	int getWords() throws RemoteException;
	ArrayList<Integer> getNumList() throws RemoteException;
	
	boolean checkUserName(String username) throws RemoteException;
	boolean checkPass(String pass) throws RemoteException;
	

}
