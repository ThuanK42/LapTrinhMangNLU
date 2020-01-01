package rmi.ud;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rmi.model.Connect;
import rmi.model.User;

public class FileImpl extends UnicastRemoteObject implements IFile {
	private static final long serialVersionUID = 1L;
	User user;
	BufferedInputStream bis;
	BufferedOutputStream bos;

	protected FileImpl() throws RemoteException {
		super();
	}

	@Override
	public boolean checkUserName(String name) throws RemoteException {
		try {
			Connection connect = Connect.getConnection();
			PreparedStatement statement = connect.prepareStatement("select username from user");
			ResultSet result = statement.executeQuery();
			String username;
			while(result.next()) {
				username = result.getString("username");
				if(username.equals(name)) {
					user = new User(name, null);
					System.out.println("ok");
					return true;
				}
			}
		} catch (SQLException e) {
			throw new RemoteException("SQL error");
		}
		return false;
	}

	@Override
	public boolean checkPass(String pass) throws RemoteException {
		if(user == null) {
			throw new RemoteException("Have to enter user name first by command: TEN your_user_name");
		}
		try {
			Connection connect = Connect.getConnection();
			PreparedStatement statement = connect.prepareStatement("select username, pass from user");
			ResultSet result = statement.executeQuery();
			String username, passData;
			while(result.next()) {
				username = result.getString("username");
				passData = result.getString("pass");
				if(user.getName().equals(username) && passData.equals(pass)) {
					user.setPass(pass);
					return true;
				}
			}
		} catch (SQLException e) {
			throw new RemoteException("SQL error");
		}
		return false;
	}

	@Override
	public void createDest(String df) throws RemoteException {
		try {
			bos = new BufferedOutputStream(new FileOutputStream(df));
		} catch (FileNotFoundException e) {
			throw new RemoteException("dest file is exist");
		}
	}

	@Override
	public void writeData(byte[] buff, int lenght) throws RemoteException {
		try {
			bos.write(buff, 0, lenght);
		} catch (IOException e) {
			throw new RemoteException("write data error");
		}
	}

	@Override
	public void closeDest() throws RemoteException {
		try {
			bos.close();
		} catch (IOException e) {
			throw new RemoteException("close data error");
		}

	}

	@Override
	public void openSource(String sf) throws RemoteException {
		try {
			bis = new BufferedInputStream(new FileInputStream(sf));
		} catch (FileNotFoundException e) {
			throw new RemoteException("source is not found");
		} 

	}

	@Override
	public byte[] readData() throws RemoteException {
		try {
			byte[] buff = new byte[10*1024];
			int data = bis.read(buff);
			if(data == -1) return null;
			byte[] res = new byte[data];
			System.arraycopy(buff, 0, res, 0, data);
			return res;
		} catch (IOException e) {
		}
		return null;
	}

	@Override
	public void closeSource() throws RemoteException {
		try {
			bis.close();
		} catch (IOException e) {
			throw new RemoteException("close file is fail");
		}
	}
	
	public static void main(String[] args) throws RemoteException {
		FileImpl fi = new FileImpl();
//		System.out.println(fi.checkUserName("admin"));
//		System.out.println(fi.checkPass("1234"));
		
	}

}
