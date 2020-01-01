package Chuong2.Bai2_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class OptionClient {

	public static void main(String[] args) throws IOException {
		System.out.println(".: Xem ngay gio hien tai :.");
		System.out.println("1. Day\n2. Month\n3. Year\n4. Hour\n5. Minute\n6. Second");

		Socket mySocket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;

		try {
			mySocket = new Socket("localhost", 8888);
			dos = new DataOutputStream(mySocket.getOutputStream());
			dis = new DataInputStream(mySocket.getInputStream());
			Scanner input = new Scanner(System.in);
			String s = null;
			while (true) {
				System.out.print("\nChon: ");
				s = input.nextLine();
				System.out.println(s + " was sent");
				dos.writeUTF(s);
				String str = dis.readUTF();
				System.out.print(str);
			}
		} catch (Exception e) {
			System.out.println("Ngat ket noi.");
			dis.close();
			dos.close();
			mySocket.close();
			e.printStackTrace();
		}
	}
}