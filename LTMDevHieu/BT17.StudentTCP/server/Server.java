package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static void main(String[] args) throws IOException {
		List<Student> list = initListStudent();

		ServerSocket server = new ServerSocket(1234);
		Socket client = server.accept();

		DataInputStream dis = new DataInputStream(client.getInputStream());
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());

		dos.writeUTF("Welcome to Student Manager Console\n");
		
		String text = "";
		String cmd = "";
		String rs = "";
		while (true) {
			text = dis.readUTF().trim();
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
				dos.writeUTF("Goodbye!");
				server.close();
			} else {
				dos.writeUTF("Command not found");
			}
			dos.writeUTF(rs);
		}
	}

	public static String list(List<Student> list) throws IOException {
		String text = "";
		for (Student s : list) {
			text += s.getSid() + "." + s.getName() + ", " + s.getAge() + "\n";
		}
		return text;
	}

	public static String findByName(List<Student> list, String name)
			throws IOException {
		String text = "";
		for (Student s : list) {
			if (s.getName().contains(name))
				text += s.getSid() + "." + s.getName() + ", " + s.getAge()
						+ "\n";
		}
		return text;
	}

	public static String findAgeUnder(List<Student> list, int age)
			throws IOException {
		String text = "";
		for (Student s : list) {
			if (s.getAge() <= age)
				text += s.getSid() + "." + s.getName() + ", " + s.getAge()
						+ "\n";
		}
		return text;
	}

	public static String findAgeUpper(List<Student> list, int age)
			throws IOException {
		String text = "";
		for (Student s : list) {
			if (s.getAge() >= age)
				text += s.getSid() + "." + s.getName() + ", " + s.getAge()
						+ "\n";
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

}
