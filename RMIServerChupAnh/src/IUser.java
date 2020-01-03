import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUser extends Remote {

	String getBanner() throws RemoteException;

	String dangKy(String ten, String ngaySinh, String noiCuTru) throws RemoteException, IOException;

	String guiAnh(String pathFileImage) throws RemoteException, IOException;

	void luuDulieu() throws RemoteException, IOException;

	String viewInfo(String maSo) throws RemoteException;

}
