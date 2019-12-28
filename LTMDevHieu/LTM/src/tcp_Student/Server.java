package tcp_Student;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	public static void main(String[] args) throws IOException {
		ArrayList<Student> arr = new Server().list();
		System.out.println("Dang cho ket noi");
		ServerSocket server = new ServerSocket(1234);
		Socket client = server.accept();
		System.out.println("Da ket noi");

		PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
		BufferedReader bs = new BufferedReader(new InputStreamReader(client.getInputStream()));

		pw.println("Welcome to Student manager Console");
		pw.println("1. findByName\n2. findByAge");

		String text = "";
		String cmd = "";
		String rs = "";

		while ((text = bs.readLine()) != null) {
			cmd = text;
			int index = text.indexOf(" ");
			if (index > 0) {
				cmd = text.substring(0, index).trim();
			}
			if (cmd.compareToIgnoreCase("findByName") == 0) {
				rs = new Server().findByName(arr, text.substring(index).trim());
			} else if (cmd.compareToIgnoreCase("findByAge") == 0) {
				String ages = text.substring(index).trim();
				int age = Integer.parseInt(ages);
				rs = new Server().findByAge(arr, age);
			} else if (cmd.compareToIgnoreCase("exit") == 0) {
				pw.print("goodbye");
				server.close();
				break;
			} else {
				rs = "Loi cu phap";
			}
			pw.print(rs);
		}

	}

	public String findByName(ArrayList<Student> list, String name) {
		String text = "";
		for (Student s : list) {
			System.out.println("check: " + s.getName() + ".contains=" + s.getName().contains(name));
			if (s.getName().contains(name)) {
				text += s.getId() + ".\t" + s.getName() + "," + s.getAge() + "\n";
			}
		}
		return text;
	}

	public String findByAge(ArrayList<Student> list, int age) {
		String text = "";
		for (Student s : list) {
			if (s.getAge() <= age) {
				text += s.getId() + ".\t" + s.getName() + "," + s.getAge() + "\n";
			}
		}
		return text;
	}

	public ArrayList<Student> list() {
		ArrayList<Student> list = new ArrayList<>();
		Student s1 = new Student("1", "Nguyen Hieu", 20);
		Student s2 = new Student("2", "Nguyen Van A", 21);
		list.add(s1);
		list.add(s2);
		return list;
	}
}
