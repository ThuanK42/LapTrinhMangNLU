package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ISinhVien extends Remote {

	public List<SinhVien> findByName(String name) throws RemoteException;

	public List<SinhVien> findByAge(String name) throws RemoteException;

	public List<SinhVien> findByScore(String name) throws RemoteException;
}
