/**
 * Thay đổi chương trình ở bài 1, cho phép người dùng nhập vào loại yêu cầu (Day,
 * Month, Year, Hour, Minute, Second), dựa vào loại yêu cầu Server trả về cho client thời gian mới
 */
package Chuong2.Bai2_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OptionServer {
	private static ServerSocket serverSocket = null;

	public static void main(String[] args) throws IOException {
		OptionServer dt = new OptionServer();
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
			while (true) {
				inline = dis.readUTF();
				char ch[] = inline.toCharArray();
				if (Character.isDigit(ch[0])) {
					int i = Integer.parseInt(inline);
					System.out.println("A Socket was instantiated");
					switch (i) {
					case 1:
						inline = "Ngay hien tai: " + now("dd");
						break;
					case 2:
						inline = "Thang hien tai: " + now("MMMM");
						break;
					case 3:
						inline = "Nam hien tai: " + now("yyyy");
						break;
					case 4:
						inline = "Gio hien tai: " + now("HH") + " g";
						break;
					case 5:
						inline = "Phut hien tai: " + now("MM") + " phut";
						break;
					case 6:
						inline = "Giay hien tai: " + now("SS") + " giay";
						break;
					default:
						inline = "UNKOWN";
					}
					dos.writeUTF(inline);
					System.out.println("Server is waiting ...");
				} else
					dos.writeUTF("Khong phai la so nguyen.");
			}
		} catch (Exception e) {
			dos.close();
			dis.close();
			serverSocket.close();
			System.out.print(e.getMessage());
		}
	}
}