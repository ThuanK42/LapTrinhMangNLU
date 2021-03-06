package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBank extends Remote {

	String hiClient() throws RemoteException;
	
	void napDuLieuBanDau() throws RemoteException;

	int checkUser(String name) throws RemoteException;

	int checkLogin(String pass) throws RemoteException;

	int guiTien(int soTien) throws RemoteException;

	int layTien(int soTien) throws RemoteException;

	int chuyenTien(String stkChuyenDen, int soTien) throws RemoteException;

}
