package tcp.ud;

import java.io.*;
import java.net.*;

public class Server {
	ServerSocket serverSocket;
	Socket socket;
	ServerProcess serverProcess;

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

	private void start() {
		try {
			serverSocket = new ServerSocket(12345);
			while (true) {
				socket = serverSocket.accept();
				serverProcess = new ServerProcess(socket);
				serverProcess.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
