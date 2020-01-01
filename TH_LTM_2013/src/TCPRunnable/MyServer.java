/**
 * Viết chương trình Client – Server sử dụng giao thức TCP thực hiện y/c sau:
 * Client: gửi y/c hỏi ngày tháng năm hiện tại lên Server.
 * Server: gửi trả lời cho Client.
 */
package TCPRunnable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyServer implements Runnable {
	private static ServerSocket serverSocket = null;

	private Socket clientSocket;

	public MyServer(Socket socket) {
		clientSocket = socket;
	}

	public static void main(String[] args) throws IOException {
		Socket socket = null;
		serverSocket = new ServerSocket(8888);
		System.out.print("Server is waiting ...\n");
		while (true) {
			socket = serverSocket.accept();
			System.out.println("A Socket was instantiated");
			new Thread(new MyServer(socket)).start();
		}

	}

	public void run() {

		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			dos = new DataOutputStream(clientSocket.getOutputStream());
			dis = new DataInputStream(clientSocket.getInputStream());
			String inline = "";
			inline = dis.readUTF();
			if (inline.equalsIgnoreCase("y")) {
				// --------------
				long current_time = System.currentTimeMillis();
				java.sql.Date NgayHienTai = new java.sql.Date(current_time);
				SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");
				inline = dayFormat.format(NgayHienTai);
				// --------------
				dos.writeUTF(inline);
				System.out.println("Ngat ket noi.");
			} else
				dos.writeUTF("Sai yeu cau!!!");
		} catch (Exception e) {
			try {
				dos.close();
				serverSocket.close();
				dis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.print(e.getMessage());
		}
	}
}