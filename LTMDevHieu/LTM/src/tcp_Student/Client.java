package tcp_Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket sc = new Socket("localhost", 1234);
		DataInputStream dis = new DataInputStream(sc.getInputStream());
		DataOutputStream dos = new DataOutputStream(sc.getOutputStream());

		System.out.println(dis.readUTF());

		Scanner scan = new Scanner(System.in);
		while (true) {
			String cmd = scan.nextLine();
			if (cmd.compareToIgnoreCase("exit") == 0) {
				System.out.println("Client: Goodbye!");
				break;
			}
			dos.writeUTF(cmd);
			String receive = dis.readUTF();
			System.out.println(receive);
		}
		scan.close();
		sc.close();

	}

}
