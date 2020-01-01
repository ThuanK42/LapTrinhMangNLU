package Chuong2.Bai2_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CurrentDateDelayClient {

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
			System.out.print("\nBan co muon xem ngay gio hien tai (y/n)? ");
			s = input.nextLine();
			dos.writeUTF(s);
			while (true) {
				String str = dis.readUTF();
				System.out.println("Ngay gio hien tai: " + str);
			}
		} catch (Exception e) {
			dis.close();
			dos.close();
			mySocket.close();
			e.printStackTrace();
		}
	}
}