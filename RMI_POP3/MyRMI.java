package RMI_POP3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRMI extends UnicastRemoteObject implements IRMI {

	BufferedInputStream bis;
	BufferedOutputStream bos;

	protected MyRMI() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkExistUserName(String username) throws RemoteException {
		for (User u : UserDAOImpl.arruser) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean login(String username, String pass) throws RemoteException {
		for (User u : UserDAOImpl.arruser) {
			if (u.getUsername().equals(username) && u.getPass().equals(pass)) {
				return true;
			}

		}
		return false;
	}

	@Override
	public User get(String usename) throws RemoteException {
		for (User u : UserDAOImpl.arruser) {
			if (u.getUsername().equals(usename)) {
				return u;
			}

		}
		return null;
	}

	@Override
	public void createDest(String s) throws RemoteException {
		try {
			bos = new BufferedOutputStream(new FileOutputStream(new File(s)));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			throw new RemoteException("file not exist");
		}

	}

	@Override
	public void writeData(byte[] b, int lenght) throws RemoteException {
		try {
			bos.write(b, 0, lenght);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void closeDest() throws RemoteException {
		try {
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void openSource(String s) throws RemoteException {
		try {
			bis = new BufferedInputStream(new FileInputStream(new File(s)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public byte[] readData() throws RemoteException {
		byte b[] = new byte[1024];
		try {
			int a = bis.read(b);
			if (a == -1) {
				return null;
			}

			byte bytes[] = new byte[a];

			System.arraycopy(b, 0, bytes, 0, a);
			return bytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void closeSource() throws RemoteException {
		try {
			bis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}