package server_demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
	public static void main(String[] args) {
		BufferedReader bf = null;
		BufferedWriter bw = null;
		Socket sc = null;
		final int port = 5555;
		final String proxy = "localhost";

		try {
			sc = new Socket(proxy, port);
			// Tạo luồng đầu ra tại client (Gửi dữ liệu lên server)
			bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			// Tạo luông đầu vào tại client (Nhận dữ liệu từ server)
			bw = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Không tìm thấy địa chỉ server" + proxy);
			return;
		} catch (IOException e) {
			System.out.println("Lỗi server" + proxy);
		}

		// Ghi dữ liệu đầu vào luồng đầu ra của Client

		try {
			bw.write("Xin chào");
			bw.newLine();
			bw.flush();

			bw.write("I am Hieu");
			bw.newLine();
			bw.flush();

			bw.write("QUIT");
			bw.newLine();
			bw.flush();

			String respon;
			while ((respon = bf.readLine()) != null) {
				System.out.println("Server :" + respon);
				if (respon.indexOf("Yes") != -1) {
					break;
				}
			}
			bf.close();
			bw.close();
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
