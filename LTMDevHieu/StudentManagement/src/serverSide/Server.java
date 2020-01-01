package serverSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int DEFAULT_PORT = 12345;
	private int port;
	private ServerSocket server;

	public Server() {
		this(DEFAULT_PORT);
	}

	public Server(int port) {
		this.port = port;
	}

	public void run() throws IOException {
		server = new ServerSocket(port);
		Socket socket;
		do {
			socket = server.accept();
			Thread serverThread = new ServerThread(socket);
			serverThread.start();
		} while (true);
	}
	
	public void close() throws IOException {
		this.server.close();
	}
}
