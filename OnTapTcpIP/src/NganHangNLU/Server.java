package NganHangNLU;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static final int PORT = 12345;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		Socket socket;
		
		System.out.println("Cho doi ket noi tu Client");
		socket = serverSocket.accept();
		System.out.println("Client da ket noi");
		ServerProcess sp = new ServerProcess(socket);
		sp.start();
		

	}

}
