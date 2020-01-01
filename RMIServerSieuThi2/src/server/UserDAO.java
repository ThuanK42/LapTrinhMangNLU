package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UserDAO extends UnicastRemoteObject implements IUser {

	protected UserDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User user = null;
	// static List<User> listUser = new ArrayList<User>();

	public List<User> getData() {
		File file = new File("src\\server\\fileuser.txt");
		List<User> list = new ArrayList<User>();
		try {
			if (!file.exists())
				System.out.println("file khong ton tai");
			if (!file.isDirectory() && file.isFile()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String lines;
//				StringTokenizer token = new StringTokenizer(lines, "");
//				
//				switch (token.nextToken()) {
//				case :
//					
//					break;
//
//				default:
//					break;
//				}
				while ((lines = br.readLine()) != null) {
					String[] arrStr = lines.trim().split("\t");
					list.add(new User(arrStr[0], arrStr[1]));
				}
				list.remove(0);
				br.close();
				return list;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int checkUser(String name) {
		List<User> listUser = getData();
		for (User user3 : listUser) {
			if (user3.getUsername().equalsIgnoreCase(name)) {
				this.user = new User(name, null);
				return 1;
			}
		}
		this.user = null;
		return 0;
	}

	public int checkLogin(String pass) {
		List<User> listUser = getData();
		if (user == null) {
			return 2;
		}
		for (User user2 : listUser) {
			// lay tung username trong list so sanh voi bien name da nhap o ham checkUser

			if (user2.getUsername().equalsIgnoreCase(this.user.getUsername())
					&& user2.getPassword().equalsIgnoreCase(pass)) {
				this.user.setPassword(pass);

				return 1;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		String line = "Le   Van   Thuan";
		String afterReplace = line.replaceAll("\\s", "");
		System.out.println(afterReplace);
	}

}
