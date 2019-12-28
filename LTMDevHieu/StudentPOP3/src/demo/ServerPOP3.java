package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerPOP3 extends Thread {
	Socket sc;
	BufferedReader bf;
	PrintWriter pw;
	final int port = 555;
	static ArrayList<Student> st = new ArrayList<Student>();
	ArrayList<User> users = new ArrayList<User>();

	public ServerPOP3(Socket sc, BufferedReader bf, PrintWriter pw) {
		super();
		this.sc = sc;
		this.bf = bf;
		this.pw = pw;
	}

	// run
	@Override
	public void run() {
		try {
			if (checkLogin(bf, pw) == true) {
				process(bf, pw);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// check login
	public boolean checkLogin(BufferedReader bf, PrintWriter pw)
			throws IOException {
		boolean result = false;
		String line = "";
		while (true)
			bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
		pw = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()), true);
		pw.println("USER name" + "\n");
		line = bf.readLine();
		String[] st = line.split(" ");
		String command = st[0];
		String info = st[1];
		for (User u : users) {
			if (command.equalsIgnoreCase("USER")) {
				if (u.getUserName().equalsIgnoreCase(info)) {
					result = true;
					pw.println(" + OK pass UserName");
				} else {
					result = false;
					pw.println(" + No pass UserName");
				}

			} else if (command.equalsIgnoreCase("PASS")) {
				pw.println("+ Ok pass");
				if (u.getPassword().equalsIgnoreCase(info)) {
					result = true;
					pw.println("+Ok pass password");
					return result;
				} else {
					result = false;
					pw.println("+ No pass password");
				}

			} else {
				pw.println("Ban nhap sai dinh dangs");
			}

		}
		return result;
	}

	// process
	public String process(BufferedReader bf, PrintWriter pw) {
		String result = "";
		return result;
	}

	public static List<Student> initListStudent() {
		st.add(new Student("1", "Nguyen Van A", 20));
		st.add(new Student("2", "Nguyen Van B", 21));
		st.add(new Student("3", "Nguyen Van C", 26));
		st.add(new Student("4", "Nguyen Van D", 22));
		st.add(new Student("5", "Nguyen Van E", 25));
		st.add(new Student("6", "Nguyen Van F", 18));
		return st;
	}

	public static void main(String[] args) {

	}
}
