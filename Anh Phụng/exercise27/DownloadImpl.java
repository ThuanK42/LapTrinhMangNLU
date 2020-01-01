package exercise27;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DownloadImpl extends UnicastRemoteObject implements IDownload {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FileInputStream> lstFis;
	

	protected DownloadImpl() throws RemoteException {
		super();
		lstFis = new ArrayList<FileInputStream>();
	}

	@Override
	public int openFile(String sf) throws RemoteException {
		try {
			FileInputStream fis = new FileInputStream(new File(sf));
			lstFis.add(fis);
			
		} catch (FileNotFoundException e) {
			throw new RemoteException(e.getMessage());
		}
		return lstFis.size() - 1;
	}

	@Override
	public byte[] readData(int id) throws RemoteException {
		byte[] data = new byte[1024];
		int length;
		
		try {
			while ((length = lstFis.get(id).read(data)) == -1) {
				return null;
			}
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		}
		
		byte[] temp = new byte[length];
		System.arraycopy(data, 0, temp, 0, length);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RemoteException(e.getMessage());
		}
		
		return temp;
	}

	@Override
	public void closeFile(int id) throws RemoteException {
		try {
			lstFis.get(id).close();
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		}
	}

}
