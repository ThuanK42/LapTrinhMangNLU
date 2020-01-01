package RMI;

import java.rmi.RemoteException;
//import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIImpl extends UnicastRemoteObject implements InFindST {

	protected RMIImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String findByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findByAge(int age) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double findByScore(double score) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
