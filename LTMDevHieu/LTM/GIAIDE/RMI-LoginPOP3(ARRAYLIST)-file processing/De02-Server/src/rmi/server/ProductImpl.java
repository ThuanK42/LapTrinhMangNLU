package rmi.server;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.StringTokenizer;

import rmi.model.DataUser;
import rmi.model.User;

public class ProductImpl extends UnicastRemoteObject implements IProduct {
	private static final long serialVersionUID = 1L;
	String totalData;
	User user;
	
	protected ProductImpl() throws RemoteException {
		super();
		user = null;
		totalData = "";
	}
	
	@Override
	public String getWelcomMessage() throws RemoteException {
		return "Welcome to words processing";
	}

	@Override
	public boolean checkUserName(String username) throws RemoteException {
		for(User u:DataUser.getData()) {
			if(u.getUsername().equals(username)) {
				user = new User(username, null);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkPass(String pass) throws RemoteException {
		if(user == null) {
			throw new RemoteException("Have to enter the username first");
		}
		for(User u:DataUser.getData()) {
			if(u.getPass().equals(pass) && u.getUsername().equals(user.getUsername())) {
				user.setPass(pass);
				return true;
			}
		}
		return false;
	}

	@Override
	public void saveDataFile(File file) throws RemoteException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-16"));
			String line;
			while((line = br.readLine()) != null) {
				totalData += line + " ";
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void saveDataString(String chuoi) throws RemoteException {
		totalData += chuoi + " ";
	}

	@Override
	public int getSum() throws RemoteException {
		StringTokenizer st = new StringTokenizer(totalData);
		int num = 0;
		int word = 0;
		int result = 0;
		
		while(st.hasMoreTokens()) {
			try {
				result += Integer.parseInt(st.nextToken());
			} catch (NumberFormatException e) {
				word += 1;
			}
		}
		return result;
	}

	@Override
	public int getNums() throws RemoteException {
		StringTokenizer st = new StringTokenizer(totalData);
		int num = 0;
		int word = 0;
		int next;
		while(st.hasMoreTokens()) {
			try {
				next = Integer.parseInt(st.nextToken());
				num += 1;
			} catch (NumberFormatException e) {
				word += 1;
			}
		}
		return num;
	}

	@Override
	public int getWords() throws RemoteException {
		StringTokenizer st = new StringTokenizer(totalData);
		int num = 0;
		int word = 0;
		int next;
		while(st.hasMoreTokens()) {
			try {
				next = Integer.parseInt(st.nextToken());
				num += 1;
			} catch (NumberFormatException e) {
				word += 1;
			}
		}
		return word;
	}

	@Override
	public ArrayList<Integer> getNumList() throws RemoteException {
		StringTokenizer st = new StringTokenizer(totalData);
		int next;
		ArrayList<Integer> res = new ArrayList<>();
		while(st.hasMoreTokens()) {
			try {
				next = Integer.parseInt(st.nextToken());
				res.add(next);
			} catch (NumberFormatException e) {
			}
		}
		return res;
	}
	
}
