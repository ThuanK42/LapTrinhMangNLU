package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISanPham extends Remote {

	public int addProduct(int idsp, String ten_san_pham, int so_luong, int gia_ban) throws RemoteException;
	public int editProduct(int idsp, String ten_san_pham, int so_luong, int gia_ban) throws	RemoteException;
	public int deleteProduct(int idsp) throws RemoteException;
	public void viewSearch(String name) throws RemoteException;
}
