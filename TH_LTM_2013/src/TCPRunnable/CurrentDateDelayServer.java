package TCPRunnable;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class CurrentDateDelayServer implements Runnable {
	Socket s;

	CurrentDateDelayServer(Socket csocket) {
		this.s = csocket;
	}

	public static void main(String[] args) throws Exception {
		ServerSocket server;
		Socket socket = null;
		try {
			server = new ServerSocket(8888);
			System.out.println("Server is started");
			while (true) {
				socket = server.accept();
				new Thread(new CurrentDateDelayServer(socket)).start();
			}
		} catch (Exception e) {
		}
	}

	public void run() {
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(s.getOutputStream());
			while (true) {
				String time = new Date().toString();
				dos.writeUTF("Client " + this.toString() + ": " + time);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
		}
	}
}
