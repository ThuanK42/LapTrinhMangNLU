package BaiThi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static ServerSocket server;

	public static void main(String[] args) {
		Server s = new Server();
		s.go();
	}

	public void go() {
		Socket socket;
		DataOutputStream dos;
		DataInputStream dis;
		try {
			server = new ServerSocket(8888);
			System.out.println("Server da san sang ...");
			socket = server.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			
			System.out.println(dis.readUTF());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
