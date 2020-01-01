package tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket serverSocket;
	Socket socket;
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start();
	}

	private void start() throws IOException {
		serverSocket = new ServerSocket(12345);
		while(true) {
			socket = serverSocket.accept();
			ProcessServer processServer = new ProcessServer(socket);
			processServer.start();
		}
		
	}

}
