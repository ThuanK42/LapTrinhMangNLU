package exercise28;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class UploadImpl extends UnicastRemoteObject implements IUpload {

	private static final long serialVersionUID = 1L;
	FileOutputStream fos;
	BufferedOutputStream bos;
	static ArrayList<BufferedOutputStream> list = new ArrayList<BufferedOutputStream>();
	protected UploadImpl() throws RemoteException {
		super();
	}

	@Override
	public int createDestFile(String df) throws RemoteException {
		try {
//			fos = new FileOutputStream(new File(df));
			bos = new BufferedOutputStream(new FileOutputStream(new File(df)));
			list.add(bos);
		} catch (FileNotFoundException e) {
			throw new RemoteException(e.getMessage());
		}
		return list.size() - 1;
	}

	@Override
	public void writeFile(byte[] buff, int length, int id) throws RemoteException {
		try {
			list.get(id).write(buff, 0, length);
//			bos.write(buff, 0, length);
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new RemoteException(e.getMessage());
		}
	}

	@Override
	public void closeDestFile(int id) throws RemoteException {
		try {
			list.get(id).close();
			bos.close();
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		}
	}

}
