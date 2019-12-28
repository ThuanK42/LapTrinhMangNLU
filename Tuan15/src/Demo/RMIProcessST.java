package Demo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIProcessST extends UnicastRemoteObject implements InFindST {

	protected RMIProcessST() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String findByLessName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		String result = "";
		ArrayList<Student> list = loadData();
		for (Student st : list) {
			if (st.getName().equalsIgnoreCase(name))
				result = st.toString();
		}
		return result;
	}

	@Override
	public String findByLessAge(int age) throws RemoteException {
		// TODO Auto-generated method stub
		String result = "";
		ArrayList<Student> list = loadData();
		for (Student st : list) {
			if (st.getAge() <= age)
				result = st.toString();
		}
		return result;
	}

	@Override
	public String findByLessScore(double score) throws RemoteException {
		// TODO Auto-generated method stub
		String result = "";
		ArrayList<Student> list = loadData();
		for (Student st : list) {
			if (st.getScore() <= score) {
				result = st.toString();
			}
		}
		return result;
	}

	public class Student {
		private String name;
		private int age;
		private double score;

		public Student(String name, int age, double score) throws RemoteException {
			super();
			this.name = name;
			this.age = age;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public double getScore() {
			return score;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "RMIProcessST [name=" + name + ", age=" + age + ", score=" + score + "]";
		}
	}

	public ArrayList<Student> loadData() throws RemoteException {
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student("Thu", 18, 4.5));

		return list;
	}
}
