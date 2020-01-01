package lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server2 extends Thread{

	static int port = 555;
	static ArrayList<Student> listStudent = new ArrayList<>();
	Socket socket;
	BufferedReader bd;
	PrintWriter pw;
	

	public Server2(Socket sc, BufferedReader bd, PrintWriter pw) {
		super();
		this.socket = sc;
		this.bd = bd;
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

	public  String findByName(String text) {
		String result="";
		for (Student st : listStudent) {
			if (st.getName().equalsIgnoreCase(text)) {
				result += st + " ** ";
			}
		}
		return result;

	}

	public  String findByUpperAge(int text) {
		String result="";
		for (Student st : listStudent) {
			if (st.getAge() >=text) {
				result += st + " ** ";
			}
		}
		return result;

	}
	public  String findByUnderAge(int text) {
		String result="";
		for (Student st : listStudent) {
			if (st.getAge() <=text) {
				result += st + " ** ";
			}
		}
		return result;

	}

	public  String findByUpperScore(double text) {
		String result="";
		for (Student st : listStudent) {
			if (st.getScore() >= text) {
				result += st + " ** ";

			}
		}
		return result;

	}
	public  String findByUnderScore(double text) {
		String result="";
		for (Student st : listStudent) {
			if (st.getScore() <=text) {
				result += st + " ** ";

			}
		}
		return result;

	}
	
	public  String findList() {
		String result="";
		for (Student st : listStudent) {
			
				result += st + " ** ";

			
		}
		return result;

	}
	@Override
	public  void run()  {
		
		// b2: nhan input user tu client
		BufferedReader receiveFromClient;
		try {
			receiveFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String inputUser = receiveFromClient.readLine();
		System.out.println("input user: " + inputUser);
		PrintWriter sendToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		// b3: xu li yeu cau
		String[] arr = inputUser.split(" ");
		String method=arr[0];

		String result ="";
		while(arr!=null) {
			if(arr.length==1) {
				if(method.equalsIgnoreCase("findList")) {
					result=findList();
				}else if (method.equalsIgnoreCase("Exit")){
					/// close connect
					System.out.println("GoodBye!");
					result="Exit";
					socket.close();
					return;
					
				}
				
			}else if(arr.length==2) {
				String findObect=arr[1];
				
				if (method.equalsIgnoreCase("findByName")) {
					System.out.println();
					result = findByName(findObect);

				}
				// tach lam 2 phuong thuc lon hon va nho hon
				if (method.equalsIgnoreCase("findByUpperAge")) {
					result = findByUpperAge(Integer.parseInt(findObect));
				}
				else if (method.equalsIgnoreCase("findByUderAge")) {
					result = findByUnderAge(Integer.parseInt(findObect));
				}else if (method.equalsIgnoreCase("findByUpperScore")) {
					result = findByUpperScore(Double.parseDouble(findObect));
				}else if (method.equalsIgnoreCase("findByUnderScore")) {
					result = findByUnderScore(Double.parseDouble(findObect));
				}else {
					/// other command
					result="No found command";
				}
				
				
			}else {
				// ko dung cu phap
				result=" No Format";
			}
		

		// b4: gui ve Client
		
		sendToClient.println(result);
		System.out.println(result);
		break;

		// b5: dong nguon
		//socket.close();
		//server.close();
		
		}} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// mo ket noi
				createList();
				ServerSocket server = new ServerSocket(port);
				Socket sc=null;
				while(true) {
				sc = server.accept();
				System.out.println("Chap nhan ket noi tu sclient");
				BufferedReader fc = new BufferedReader(new InputStreamReader(sc.getInputStream()));
				PrintWriter pw= new PrintWriter(new OutputStreamWriter(sc.getOutputStream()),true);
				Server2  ss= new Server2(sc, fc, pw);
				ss.start();
				}


	}
}
