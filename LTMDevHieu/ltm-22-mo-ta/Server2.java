
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server2 extends Thread {
	static int client = 0;
	static int port = 555;
	static ArrayList<Student> listStudent = new ArrayList<>();
	Socket sc;
	BufferedReader br;
	PrintWriter pw;

	public Server2(Socket sc, BufferedReader br, PrintWriter pw) {
		super();
		this.sc = sc;
		this.br = br;
		this.pw = pw;
	}

	public static void createList() {
		Student st1 = new Student("da", 20, 10);
		Student st2 = new Student("hoa", 19, 8.5);
		Student st3 = new Student("nha", 26, 9.8);
		Student st4 = new Student("thy", 20, 3.2);
		Student st5 = new Student("loc", 13, 10);
		Student st6 = new Student("phuoc", 19, 10);
		Student st7 = new Student("chau", 22, 10);
		Student st8 = new Student("sa", 20, 1.5);
		Student st9 = new Student("ky", 20, 7.9);
		listStudent.add(st1);
		listStudent.add(st2);
		listStudent.add(st3);
		listStudent.add(st4);
		listStudent.add(st5);
		listStudent.add(st6);
		listStudent.add(st7);
		listStudent.add(st8);
		listStudent.add(st9);

	}

	public String findByName(String text) {
		String result = "";
		for (Student st : listStudent) {
			if (st.getName().equalsIgnoreCase(text)) {
				result += st + " ** ";
			}
		}
		return result;

	}

	public String findByUpperAge(int text) {
		String result = "";
		for (Student st : listStudent) {
			if (st.getAge() >= text) {
				result += st + " ** ";
			}
		}
		return result;

	}

	public String findByUnderAge(int text) {
		String result = "";
		for (Student st : listStudent) {
			if (st.getAge() <= text) {
				result += st + " ** ";
			}
		}
		return result;

	}

	public String findByUpperScore(double text) {
		String result = "";
		for (Student st : listStudent) {
			if (st.getScore() >= text) {
				result += st + " ** ";

			}
		}
		return result;

	}

	public String findByUnderScore(double text) {
		String result = "";
		for (Student st : listStudent) {
			if (st.getScore() <= text) {
				result += st + " ** ";

			}
		}
		return result;

	}

	public String findList() {
		String result = "";
		for (Student st : listStudent) {

			result += st + " ** ";

		}
		return result;

	}

	@Override
	public void run() {
		System.out.println("Client " + client + ": Waiting input of client");
		// b2: nhan input user tu client
		try {
			br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			String inputUser = br.readLine();
			System.out.println("Client " + client + ": Client input: " + inputUser);
			pw = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()), true);
			// b3: xu li yeu cau
			String[] arr = inputUser.split(" ");
			String method = arr[0];

			String result = "";
			while (arr != null) {
				if (arr.length == 1) {
					if (method.equalsIgnoreCase("findList")) {
						result = findList();
					} else if (method.equalsIgnoreCase("Exit")) {
						/// close connect
						System.out.println("GoodBye!");
						result = "Exit";
						sc.close();
						return;
					} else {
						result = "Invalid Format";
					}
				} else if (arr.length == 2) {
					String findObect = arr[1];
					if (method.equalsIgnoreCase("findByName")) {
						result = findByName(findObect);
					} else
					// tach lam 2 phuong thuc lon hon va nho hon
					if (method.equalsIgnoreCase("findByUpperAge")) {
						result = findByUpperAge(Integer.parseInt(findObect));
					} else if (method.equalsIgnoreCase("findByUderAge")) {
						result = findByUnderAge(Integer.parseInt(findObect));
					} else if (method.equalsIgnoreCase("findByUpperScore")) {
						result = findByUpperScore(Double.parseDouble(findObect));
					} else if (method.equalsIgnoreCase("findByUnderScore")) {
						result = findByUnderScore(Double.parseDouble(findObect));
					} else {
						/// other command
						result = "Not found command";
					}

				} else {
					// ko dung cu phap
					result = "Invalid Format";
				}

				// b4: gui ve Client

				pw.println(result);
				System.out.println("Client " + client + ": Result to client: " + result + "\n*************");
				break;

				// b5: dong nguon
				// socket.close();
				// server.close();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void runServer() throws IOException {
		// create student list

		createList();

		// establish server

		ServerSocket server = new ServerSocket(port);
		Socket sc = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		System.out.println("Welcome to Student Manager System");
		while (true) {
			int nextClient = client + 1;
			System.out.println("Waiting client: " + nextClient);
			sc = server.accept();
			System.out.print("Accepted client: ");
			System.out.println(++client);
			// establish server inputstream
			br = new BufferedReader(new InputStreamReader(sc.getInputStream()));

			// establish server outputstream
			pw = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()), true);

			// create server obj
			Server2 ss = new Server2(sc, br, pw);
			// start server thread, first time not recieve because not establish inputstream
			ss.start();

			// sau khi chấp nhận 1 client, chạy hàm start 1 thread cho client đó.
			// Lúc này có 2 thread song song đó là thread server2 chính và thread server2
			// vừa tạo
			// Thread chính tiếp tục vòng lặp while true đó là lắng nghe server.accept() cho
			// client tiếp theo

		}

	}

	public static void main(String[] args) throws IOException {
		runServer();
	}
}
