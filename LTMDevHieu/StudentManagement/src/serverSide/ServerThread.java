package serverSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;

import serverSide.dao.AccountDAO;//thay thế 2 import này để dùng database thay vì DAO
import serverSide.dao.StudentDAO;
import serverSide.model.Student;

public class ServerThread extends Thread {
	private static final String DEFAULT_CSN = "UTF-8";
	private Socket socket;
	private BufferedReader netIn;
	private PrintWriter netOut;
	private String line;
	private String criteria;
	private String value;
	ArrayList<Student> filteredStudents;

	public ServerThread(Socket socket) throws UnsupportedEncodingException, IOException {
		this(socket, DEFAULT_CSN);
	}

	public ServerThread(Socket socket, String csn) throws UnsupportedEncodingException, IOException {
		this.socket = socket;
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream(), csn));
		netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), csn), true);
	}

	@Override
	public void run() {
		netOut.println("Welcome to Student Managerment Server!");
		boolean isSignedIn = login();
		while (isSignedIn) {
			try {
				if ((line = netIn.readLine()).equals("EXIT"))
					break;
				analyseRequest();
				processRequest(criteria, value);
				sendRespond();
			} catch (Exception e) {
				netOut.println(e.getMessage());
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void analyseRequest() throws Exception {
		String[] elements = line.replaceAll("\\s+", " ").trim().split(" ");
		if (elements.length != 2) {
			throw new Exception("Invalid command!");
		}
		switch (elements[0].toUpperCase()) {
		case "FINDBYID":
			criteria = "ID";
			break;
		case "FINDBYNAME":
			criteria = "NAME";
			break;
		case "FINDBYSCORE":
			criteria = "SCORE";
			break;

		default:
			break;
		}
		value = elements[1];
	}

	private void processRequest(String criteria, String value) throws Exception {
		switch (criteria) {
		case "ID":
			filteredStudents = StudentDAO.findByID(value);
			break;
		case "NAME":
			filteredStudents = StudentDAO.findByName(value);
			break;
		case "SCORE":
			double score;
			try {
				score = Double.parseDouble(value);
			} catch (NumberFormatException e) {
				throw new Exception("Score must be number!");
			}
			filteredStudents = StudentDAO.findByScore(score);
			break;
		default:
			netOut.println("Invalid command!");
			break;
		}
	}

	private void sendRespond() {
		for (Student student : filteredStudents) {
			netOut.println(student);
		}
	}

	private boolean login() {
		boolean isSignedIn = false;
		String line = "";
		String lastUsername = null;
		do {
			try {
				line = netIn.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (line.equalsIgnoreCase("EXIT"))
				break;
			String[] elements = line.replaceAll("\\s+", " ").trim().split(" ");
			if (elements.length != 2) {
				netOut.println("Invalid command!");
				continue;
			}
			switch (elements[0].toUpperCase()) {
			case "USER":
				lastUsername = elements[1];
				netOut.println(AccountDAO.isUserExist(lastUsername) ? "User accepted" : "User not exist");
				break;
			case "PASS":
				isSignedIn = AccountDAO.login(lastUsername, elements[1]);
				netOut.println(isSignedIn ? "Logged in" : "Wrong password");
				break;
			default:
				netOut.println("Invalid command!");
				break;
			}
		} while (!isSignedIn);
		return isSignedIn;
	}

}
