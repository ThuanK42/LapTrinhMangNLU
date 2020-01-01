package tbq.rmi.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import tbq.rmi.model.Data;
import tbq.rmi.model.User;

public class FileImpl extends UnicastRemoteObject implements IFile {
	private static final long serialVersionUID = 1L;

	private ArrayList<User> data;
	private User user;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;

	protected FileImpl() throws RemoteException {
		super();
		data = Data.getData();
		user = null;
	}

	@Override
	public boolean findName(String name) throws RemoteException {
		for (User u : data) {
			if (u.getName().equals(name)) {
				user = new User(name, null);
				return true;
			}
		}
		this.user = null;
		return false;
	}

	@Override
	public boolean checkPass(String pass) throws RemoteException {
		if (user == null) {
			throw new RemoteException("user name is null, please enter: TEN user_name");
		}
		for (User u : data) {
			if (u.getName().equals(user.getName()) && u.getPass().equals(pass)) {
				this.user.setPass(pass);
				return true;
			}
		}
		return false;
	}

	@Override
	public void createDest(String df) throws RemoteException {
		try {
			bos = new BufferedOutputStream(new FileOutputStream(df));
		} catch (FileNotFoundException e) {
			throw new RemoteException("can not create file");
		}

	}

	@Override
	public void writeData(byte[] buff, int lenght) throws RemoteException {
		try {
			bos.write(buff, 0, lenght);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void closeDest() throws RemoteException {
		try {
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void openSource(String sf) throws RemoteException {
		try {
			bis = new BufferedInputStream(new FileInputStream(sf));
		} catch (FileNotFoundException e) {
			throw new RemoteException("not found source file");
		}
	}

	@Override
	public byte[] readData() throws RemoteException {
		try {
			byte[] buff = new byte[10 * 1024];
			int data;
			data = bis.read(buff);
			if(data == -1) return null;
			
			byte[] res = new byte[data];
			System.arraycopy(buff, 0, res, 0, data);
			return res;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void closeSource() throws RemoteException {
		try {
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
