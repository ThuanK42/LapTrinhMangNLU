package manSERVER2014_15;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SanPhamDAOImpt extends UnicastRemoteObject implements MySanPham{
     
	ArrayList<User>arrUser=new ArrayList<>();
	
	public SanPhamDAOImpt(ArrayList<User> arrUser) throws RemoteException {
		super();
		this.arrUser = arrUser;
	}

	
	public ArrayList<User> getArrUser() {
		return arrUser;
	}


	public void setArrUser(ArrayList<User> arrUser) {
		this.arrUser = arrUser;
	}


	public SanPhamDAOImpt() throws RemoteException {
		arrUser=SanPhamDAO.arrUser;
		
	}

	@Override
	public boolean checkExistUserName(String username) throws RemoteException {
	for(User u: arrUser) {
		
		if(u.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
	}
		
		return false;
	}

	@Override
	public boolean login(String username,String pass) throws RemoteException {
		for(User u: arrUser ) {
			if(u.getUsername().equalsIgnoreCase(username)&& u.getPass().equalsIgnoreCase(pass)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public User get(String username) throws RemoteException {
		for(User u: arrUser) {
			if(u.getUsername().equalsIgnoreCase(username)) {
				return u;
			}
		}
		return null;
	}

	public static void main(String[] args) throws RemoteException {
		MySanPham my=new SanPhamDAOImpt();
		System.out.println(my.checkExistUserName("mane"));
	}
}