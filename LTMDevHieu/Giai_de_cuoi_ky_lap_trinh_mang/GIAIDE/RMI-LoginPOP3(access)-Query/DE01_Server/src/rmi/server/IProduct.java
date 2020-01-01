package rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import rmi.model.SanPham;

public interface IProduct extends Remote{
	String getWelcome() throws RemoteException;
	boolean checkUserName(String username) throws RemoteException;
	boolean checkPass(String pass) throws RemoteException;
	
	boolean add(int idsp, String ten_san_pham, int so_luong, double gia_ban) throws RemoteException;
	boolean remove(int idsp) throws RemoteException;
	boolean edit(int idsp, String ten_san_pham, int so_luong, double gia_ban) throws RemoteException;
	String view(String ten_san_pham) throws RemoteException;
} 
