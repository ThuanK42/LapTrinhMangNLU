package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerTelnet {

	public static void main(String[] args) throws IOException {
		List<Student> list = initListStudent();

		ServerSocket server = new ServerSocket(1234);
		Socket client = server.accept();
		PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				client.getInputStream()));

		pw.println("Welcome to Student Manager Console");

		String text = "";
		String cmd = "";
		String rs = "";
		while ((text = br.readLine()) != null) {
			cmd = text;
			int index = text.indexOf(" ");
			if (index > 0)
				cmd = text.substring(0, index).trim();

			if (cmd.compareToIgnoreCase("list") == 0) {
				rs = list(list);
			} else if (cmd.compareToIgnoreCase("findByName") == 0) {
				rs = findByName(list, text.substring(index).trim());
			} else if (cmd.compareToIgnoreCase("findAgeUnder") == 0) {
				String ageStr = text.substring(index).trim();
				int age = Integer.parseInt(ageStr);
				rs = findAgeUnder(list, age);
			} else if (cmd.compareToIgnoreCase("findAgeUpper") == 0) {
				String ageStr = text.substring(index).trim();
				int age = Integer.parseInt(ageStr);
				rs = findAgeUpper(list, age);
			} else if ((cmd.compareToIgnoreCase("exit") == 0)
					|| (cmd.compareToIgnoreCase("quit") == 0)) {
				pw.println("Goodbye!");
				server.close();
				break;
			} else {
				rs = "Command not found";
			}
			pw.println(rs);
		}
	}

	public static String list(List<Student> list) throws IOException {
		String text = "";
		for (Student s : list) {
			text += s.getSid() + "." + s.getName() + ", " + s.getAge() + "\n\r";
		}
		return text;
	}

	public static String findByName(List<Student> list, String name)
			throws IOException {
		System.out.println("findByName:[" + name + "]");
		String text = "";
		for (Student s : list) {
			System.out.println("check:" + s.getName() + ".constains="
					+ s.getName().contains(name));
			if (s.getName().contains(name))
				text += s.getSid() + "." + s.getName() + ", " + s.getAge()
						+ "\n\r";
		}
		return text;
	}

	public static String findAgeUnder(List<Student> list, int age)
			throws IOException {
		String text = "";
		for (Student s : list) {
			if (s.getAge() <= age)
				text += s.getSid() + "." + s.getName() + ", " + s.getAge()
						+ "\n\r";
		}
		return text;
	}

	public static String findAgeUpper(List<Student> list, int age)
			throws IOException {
		String text = "";
		for (Student s : list) {
			if (s.getAge() >= age)
				text += s.getSid() + "." + s.getName() + ", " + s.getAge()
						+ "\n\r";
		}
		return text;
	}

	public static List<Student> initListStudent() {
		List<Student> list = new ArrayList<>();
		list.add(new Student("1", "Nguyen Van A", 20));
		list.add(new Student("2", "Nguyen Van B", 21));
		list.add(new Student("3", "Nguyen Van C", 26));
		list.add(new Student("4", "Nguyen Van D", 22));
		list.add(new Student("5", "Nguyen Van E", 25));
		list.add(new Student("6", "Nguyen Van F", 18));
		return list;
	}

	public boolean checkLogin(Reader userName, String pass) {
		BufferedReader bf = new BufferedReader(userName);
		return false;
	}

}
