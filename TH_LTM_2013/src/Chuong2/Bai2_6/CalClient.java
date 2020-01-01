package Chuong2.Bai2_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CalClient {
	public static void main(String[] args) {
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Scanner input = new Scanner(System.in);
		try {
			Socket socket = new Socket("localhost", 8888);

			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());

			float a, b;
			String phep_toan = "";

			while (true) {
				System.out.print("Nhap a = ");
				a = input.nextFloat();

				System.out.print("Nhap b = ");
				b = input.nextFloat();

				System.out.print("Nhap phep toan (+ - * /): ");
				phep_toan = input.next();

				dos.writeUTF(a + "#" + b + "#" + phep_toan);

				System.out.println(dis.readUTF());

				System.out.print("Do you want to continue (y/n) ? ");
				String tt = input.next();
				if (tt.equalsIgnoreCase("n"))
					break;
				System.out.println("----------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
