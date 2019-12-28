package lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client2 {
	static String host = "127.0.0.1";
	static int port = 555;

	public static void findStudent() throws IOException, ClassNotFoundException {
		while(true) {
		// mo ket noi
		Socket socket = new Socket(host, port);
		System.out.println("Gui ket noi toi server");
		// b1: nhan input user tu ban phim
		BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1. findByName name");
		System.out.println("2. findByAge age");
		System.out.println("3. findByScore score");
		String user = inputUser.readLine();

		// b2: gui input user len server
		PrintWriter sendServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		sendServer.println(user);
		System.out.println("send server: " + user);

		// b4: nhan KQ gui ve tu server
		BufferedReader receiveFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String result = receiveFromServer.readLine();
		System.out.println(result);
		// b5: dong nguon
		if (result == "") {
			System.out.println("Khong tim thay du lieu");
		} else if(result.equalsIgnoreCase("Exit")) {
			System.out.println("Client GoodBye!");
			socket.close();
			return;
		}else {
			System.out.println("From Server  " +result);
		}
		}

		

	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		findStudent();
	}

}
