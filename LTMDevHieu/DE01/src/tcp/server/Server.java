package tcp.server;

import java.io.*;
import java.net.*;

public class Server {
	ServerSocket severSocket;
	Socket socket;
	

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start();
	}

	private void start() throws IOException {
		severSocket = new ServerSocket(55555);
		while (true) {
			socket = severSocket.accept();
			ServerProcess serverProcess = new ServerProcess(socket);
			serverProcess.start();
		}
	}

}
