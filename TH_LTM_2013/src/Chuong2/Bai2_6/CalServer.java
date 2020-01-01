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

public class CalServer {

	private static ServerSocket server = null;

	public static void main(String[] args) throws IOException {
		CalServer s = new CalServer();
		s.go();
	}

	public void go() throws IOException {
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			Socket clientSocket = null;
			server = new ServerSocket(8888);
			System.out.println("Server is waiting ...");
			clientSocket = server.accept();

			dos = new DataOutputStream(clientSocket.getOutputStream());
			dis = new DataInputStream(clientSocket.getInputStream());

			while (true) {
				String msg = dis.readUTF();
				System.out.println(msg);
				String[] arr = msg.split("#");
				String sendMsg = "";
				float a, b;
				a = Float.parseFloat(arr[0]);
				b = Float.parseFloat(arr[1]);
				switch (arr[2]) {
				case "+":
					sendMsg = String.valueOf(a + b);
					break;
				case "-":
					sendMsg = String.valueOf(a - b);
					break;
				case "*":
					sendMsg = String.valueOf(a * b);
					break;
				case "/":
					if (b != 0)
						sendMsg = String.valueOf(a / b);
					else
						sendMsg = "Error: Divided by zero!!!";
					break;
				}
				if (!sendMsg.equals("Error: Divided by zero!!!"))
					dos.writeUTF("Ket qua: " + a + " " + arr[2] + " " + b
							+ " = " + sendMsg);
				else
					dos.writeUTF(sendMsg);
			}

		} catch (Exception e) {
			dos.close();
			dis.close();
			server.close();
			System.out.println("Ngat ket noi!");
		}
	}
}
