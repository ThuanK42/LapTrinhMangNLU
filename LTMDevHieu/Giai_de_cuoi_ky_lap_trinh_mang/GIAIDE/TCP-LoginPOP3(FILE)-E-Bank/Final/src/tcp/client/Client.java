package tcp.client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	Socket socket;
	DataInputStream netIn;
	DataOutputStream netOut;
	BufferedReader userIn;
	boolean isLogin;

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.start();
	}

	private void start() throws IOException {
		socket = new Socket("127.0.0.1", 12345);
		netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		userIn = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(netIn.readUTF());
		isLogin = checkLogin();

		String line, command, so_tien , tk;
		String result = "";
		StringTokenizer st;
		while (isLogin) {
			line = userIn.readLine();
			if ("QUIT".equalsIgnoreCase(line))
				break;
			st = new StringTokenizer(line);
			command = st.nextToken();
			switch (command) {
			case "GUI":
				so_tien = st.nextToken();
				netOut.writeUTF(command + " " +  so_tien);
				netOut.flush();
				result = netIn.readUTF();
				break;
			case "LAY":
				so_tien = st.nextToken();
				netOut.writeUTF(command + " " +  so_tien);
				netOut.flush();
				result = netIn.readUTF();
				break;
			case "CHUYEN":
				tk = st.nextToken();
				so_tien = st.nextToken();
				netOut.writeUTF(command + " " +  tk + " "  + so_tien);
				netOut.flush();
				result = netIn.readUTF();
				break;

			default:
				result = "ERROR COMMAND";
				break;
			}
			System.out.println(result);
		}
		netIn.close();
		netOut.close();
		userIn.close();
		socket.close();
	}

	private boolean checkLogin() throws IOException {
		String line, command, username, pass;
		String result = "";
		StringTokenizer st;
		while (true) {
			line = userIn.readLine();
			if ("QUIT".equalsIgnoreCase(line))
				return false;
			st = new StringTokenizer(line);
			command = st.nextToken();
			switch (command) {
			case "TEN":
				username = st.nextToken();
				netOut.writeUTF(command + " " + username);
				netOut.flush();
				result = netIn.readUTF();
				break;
			case "MATKHAU":
				pass = st.nextToken();
				netOut.writeUTF(command + " " + pass);
				netOut.flush();
				result = netIn.readUTF();
				if ("OK".equalsIgnoreCase(result)) {
					System.out.println(result);
					return true;
				}
				break;
			default:
				result = "ERROR COMMAND";
				break;
			}
			System.out.println(result);
		}
	}

}
