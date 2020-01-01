package Chuong2.Bai2_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NumberTCPSimpleServer {
	private static ServerSocket serverSocket = null;

	public static void main(String[] args) throws IOException {
		NumberTCPSimpleServer dt = new NumberTCPSimpleServer();
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
			while (true) {
				inline = dis.readUTF();
				char ch[] = inline.toCharArray();
				if (Character.isDigit(ch[0])) {
					int i = Integer.parseInt(inline);
					System.out.println("A Socket was instantiated");
					switch (i) {
					case 1:
						inline = "ONE";
						break;
					case 2:
						inline = "TWO";
						break;
					case 3:
						inline = "THREE";
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
			serverSocket.close();
			dis.close();
			System.out.print(e.getMessage());
		}
	}
}