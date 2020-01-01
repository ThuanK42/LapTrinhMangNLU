package tcp.ud;

import java.net.*;

public class Server {
	private ServerSocket serverSocket;
	private static final int PORT = 12345;

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.start();
	}

	private void start() throws Exception {
		serverSocket = new ServerSocket(PORT);
		while (true) {
			Socket socket = serverSocket.accept();
			ServerProcess serverProcess = new ServerProcess(socket);
			serverProcess.start();
		}
	}

}
