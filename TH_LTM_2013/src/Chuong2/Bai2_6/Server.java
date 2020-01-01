/**
 * Viết chương trình client/ Server cho phép người dùng nhập vào 2 số thực
 * và một phép toán (+, -, *, /) rồi gửi đến chương trình Server.
 * Chương trình Server thực hiện tính toán kết quả dựa vào phép toán tương ứng và trả kết quả cho chương trình Client.
 */
package Chuong2.Bai2_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		String so1, so2, so3, so4, so5, so6;
		float tong, hieu, tich, thuong;
		// tao server socket
		ServerSocket server = new ServerSocket(1234);
		System.out.println("Server is now already");
		// tao 1 socket do ket noi tu client toi server
		Socket connectionSocket = server.accept();
		// tao luong nhan du lieu tu client
		DataInputStream inFromClient = new DataInputStream(
				connectionSocket.getInputStream());
		// tao luong gui du lieu toi client
		DataOutputStream outToClient = new DataOutputStream(
				connectionSocket.getOutputStream());
		// truyen du lieu tu client vao 2 bien so1 va so2
		so1 = inFromClient.readLine();
		so2 = inFromClient.readLine();
		// ep so1 va so2 tu kieu String sang kieu Integer
		float a = Integer.parseInt(so1);
		float b = Integer.parseInt(so2);
		// tinh tong a + b
		tong = a + b;
		// ep tong 2 so a+b sang kieu String
		so3 = String.valueOf(tong);
		// gui so3 ve client
		outToClient.writeBytes(so3 + '\n');

		// tinh a - b
		hieu = a - b;
		// ep 2 so a-b sang kieu String
		so4 = String.valueOf(hieu);
		// gui so4 ve client
		outToClient.writeBytes(so4 + '\n');
		// tinh a * b
		tich = a * b;
		// ep 2 so a*b sang kieu String
		so5 = String.valueOf(tich);
		// gui so5 ve client
		outToClient.writeBytes(so5 + '\n');
		// tinh a / b
		thuong = a / b;
		// ep 2 so a/b sang kieu String
		so6 = String.valueOf(thuong);
		// gui so6 ve client
		outToClient.writeBytes(so6 + '\n');
		// dong luong nhan du lieu tu client
		inFromClient.close();
		// dong luong gui du lieu ve client
		outToClient.close();
		// dong server socket
		server.close();
	}

}