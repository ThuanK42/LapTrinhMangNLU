package server_demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		String line;
		ServerSocket sc = null;
		Socket socket = null;
		BufferedReader bf;
		BufferedWriter bw;
		try {
			// Cổng kết nối với cổng client
			sc = new ServerSocket(5555);
		} catch (IOException e) {
			System.exit(1);
			e.printStackTrace();
		}

		try {
			System.out.println("Đang đợi kết nối đến server");
			// Chấp nhận kết nối với client
			socket = sc.accept();
			System.out.println("Đã kết nối đến server");

			// Mở luồng vào ra
			bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// Nhận dữ liệu từ người dùng và gửi trả lại
			while (true) {
				line = bf.readLine();

				bw.write(">>" + line);

				bw.newLine();

				bw.flush();

				if (line.equals("QUIT")) {
					bw.write(">> Yes");
					bw.newLine();
					bw.flush();
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server Stopped");
	}
}
