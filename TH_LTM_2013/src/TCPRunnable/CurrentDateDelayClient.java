package TCPRunnable;

import java.io.DataInputStream;
import java.net.Socket;

public class CurrentDateDelayClient {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 8888);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		while (true) {
			String time = din.readUTF();
			System.out.println(time);
		}

	}
}
