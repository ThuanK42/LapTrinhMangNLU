/**
 * Thay đổi chương trình ở bài 1 sao cho cứ 10 giây chương trình Server trả về cho client thời gian mới.
 */
package Chuong2.Bai2_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDateDelayServer {
	private static ServerSocket serverSocket = null;

	public static void main(String[] args) throws IOException {
		CurrentDateDelayServer dt = new CurrentDateDelayServer();
		dt.go();
	}

	public static String now(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
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
				while (true) {
					System.out.println("A Socket was instantiated");
					inline = now("dd.MM.yyyy G 'at' hh:mm:ss z");
					dos.writeUTF(inline);
					Thread.sleep(1000);
				}
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