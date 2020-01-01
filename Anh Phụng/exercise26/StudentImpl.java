package exercise26;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class StudentImpl extends UnicastRemoteObject implements IStudent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PORT = 12345;
	ArrayList<Student> list = Student.loadDataBase();

	protected StudentImpl() throws RemoteException {
		super();
	}

	@Override
	public String findByName(String name) throws RemoteException {
		String txt = "";
		for (Student st : list) {
			if (st.getName().equalsIgnoreCase(name)) {
				txt = st.getName() + "\t" + st.getAge() + "\t" + st.getScore();
			}
		}
		return txt;
	}

	@Override
	public String findByAge(double age) throws RemoteException {
		String txt = "";
		for (Student st : list) {
			if (st.getAge() == age) {
				txt += st.getName() + "\t" + st.getAge() + "\t" + st.getScore() + "\n";
			}
		}
		return txt;
	}

	@Override
	public String findByLessScore(double score) throws RemoteException {
		String txt = "";

		for (Student st : list) {
			if (st.getScore() < score) {
				txt += st.getName() + "\t" + st.getAge() + "\t" + st.getScore() + "\n";
			}
		}

		return txt;
	}

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		
		System.out.println("Listening...");
		
		Registry reg = LocateRegistry.createRegistry(PORT);
//		StudentImpl is = new StudentImpl();
		reg.bind("FindStudent", new StudentImpl());
		System.out.println("Server: Accepted!!");
	}

};