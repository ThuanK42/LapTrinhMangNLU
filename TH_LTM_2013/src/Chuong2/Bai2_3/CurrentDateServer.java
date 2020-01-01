/**
 * Viết chương trình Client – Server sử dụng giao thức TCP thực hiện y/c sau:
 * Client: gửi y/c hỏi ngày tháng năm hiện tại lên Server.
 * Server: gửi trả lời cho Client.
 */
package Chuong2.Bai2_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDateServer {
	private static ServerSocket serverSocket = null;

	public static void main(String[] args) throws IOException {
		CurrentDateServer dt = new CurrentDateServer();
		dt.go();
	}

	public void go() throws IOException {

		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			serverSocket = new ServerSocket(8888);
			System.out.print("Server is waiting ...\n");
			Socket clientSocket = null;
			clientSocket = serverSocket.accept();
			dos = new DataOutputStream(clientSocket.getOutputStream());
			dis = new DataInputStream(clientSocket.getInputStream());
			String inline = "";
			inline = dis.readUTF();
			if (inline.equalsIgnoreCase("y")) {
				System.out.println("A Socket was instantiated");
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

			dos.close();
			serverSocket.close();
			dis.close();
			System.out.print(e.getMessage());
		}
	}
}