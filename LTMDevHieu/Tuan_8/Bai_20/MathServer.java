package Tuan_8.Bai_20;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MathServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(12345);
		while (true) {
			Socket socket = serverSocket.accept();
			MathServerThread thread = new MathServerThread(socket);
			thread.start();
		}
	}
}
