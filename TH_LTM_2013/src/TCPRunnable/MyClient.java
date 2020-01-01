package TCPRunnable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

	public static void main(String[] args) throws IOException {
		// TODO code application logic here

		Socket mySocket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;

		try {
			mySocket = new Socket("localhost", 8888);
			dos = new DataOutputStream(mySocket.getOutputStream());
			dis = new DataInputStream(mySocket.getInputStream());
			Scanner input = new Scanner(System.in);
			String s = null;
			System.out
					.print("\nBan co muon xem ngay thang nam hien tai? (y/n): ");
			s = input.nextLine();
			dos.writeUTF(s);
			String str = dis.readUTF();
			System.out.print("Ngay thang nam hien tai: " + str);
		} catch (Exception e) {
			dis.close();
			dos.close();
			mySocket.close();
			e.printStackTrace();
		}
	}
}