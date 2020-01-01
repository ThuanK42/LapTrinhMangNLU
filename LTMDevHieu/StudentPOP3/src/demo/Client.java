package demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Socket socket = new Socket("127.0.0.1", 1234);

		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		System.out.println(dis.readUTF());
		
		Scanner scan = new Scanner(System.in);
		while (true) {
			String cmd = scan.nextLine();
			if (cmd.compareToIgnoreCase("exit") == 0) {
				System.out.println("[CLIENT] Goodbye!");
				break;
			}
			dos.writeUTF(cmd);
			String received = dis.readUTF();
			System.out.println(received);
		}
		scan.close();
		socket.close();
	}
}
