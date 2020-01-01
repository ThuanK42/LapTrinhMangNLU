package BaiThi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8888);
			DataOutputStream dos = new DataOutputStream(
					socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());

			String msg = ""; // chuoi de gui qua den Server

			int n = 5;
			// Nhap mang gom n phan tu
			Scanner nhap = new Scanner(System.in);
			float[] mang = new float[n];
			System.out.println("Nhap mang gom " + n + " phan tu:");
			for (int i = 0; i < n; i++) {
				System.out.print("a[" + (i + 1) + "] = ");
				mang[i] = nhap.nextFloat();
				msg += mang[i] + "#"; // dua tung phan tu cua mang vao chuoi can gui
			}
			
			dos.writeUTF(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
