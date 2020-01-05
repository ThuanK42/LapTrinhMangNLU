package QuanLySieuThi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static final int PORT = 1212;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		
			System.out.println("Cho ket noi");
			Socket socket = serverSocket.accept();
			System.out.println("Chap nhan ket noi");
			ServerProcess serverProcess = new ServerProcess(socket);
			serverProcess.start();
	

	}
}
