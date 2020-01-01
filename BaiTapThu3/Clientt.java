package BaiTapThu3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Clientt {
	static int port = 9999;
	static String localHost = "127.0.0.1";

	public static void main(String[] args) {
		System.out.println("Send require connect Server .........");
		try {

			System.out.println("Connected to Server ......");
			Socket socket = new Socket(localHost, port);
			System.out.println("Connected to Server ......");

			// gui den sv
			DataOutputStream sendServer = new DataOutputStream(socket.getOutputStream());
			// nhan tu sv
			DataInputStream fromServer = new DataInputStream(socket.getInputStream());

			// doc lenh tu nguoi dung
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

			while (true) {

				System.out.println("Correct format: ten \tab xemtienvienphi");
				System.out.println("Correct format: ten \tab dongtienvienphi \t tien");
				System.out.println("Correct format: ten \tab xembenh");

				String sendServer2 = userInput.readLine();
				sendServer.writeUTF(sendServer2);// push len hang doi
				sendServer.flush();// day qua sv

				// nhan ket qua tra ve
				String result = fromServer.readUTF();
				System.out.println(result);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
