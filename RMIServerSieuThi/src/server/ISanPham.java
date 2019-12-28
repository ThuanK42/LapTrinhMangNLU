package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ISanPham extends Remote {

	int addProduct(int id, String name, int soluong, int giaban) throws RemoteException;;

	int deleteSPById(int idsp) throws RemoteException;;

	int editProduct(int idsp, String tensp, int soluong, int giaban)throws RemoteException;;

	List<SanPham> searchProductByName(String tensp)throws RemoteException;;

}
