package KetNoiDuLieu;

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

		try {
			// client di vao cong cua server da tao, moi client dc danh dau boi dia chi duy
			// nhat localhost
			System.out.println("Send require connect Server .........");
			Socket socket = new Socket(localHost, port);
			System.out.println("Connected to Server ......");
			// gui cau lenh di
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			// nhan ket qua tu server
			DataInputStream in = new DataInputStream(socket.getInputStream());

			// doc lenh tu nguoi dung
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.println("Correct format: ten \tab xemtienvienphi");
				System.out.println("Correct format: ten \tab xembenh");
				System.out.println("Correct format: ten \tab dongtienvienphi");

				String userComm = userInput.readLine();// doc tung dong lenh
				out.writeUTF(userComm);// push len hang doi
				out.flush();// day qua sv
				// nhan ket qua tra ve
				String result = in.readUTF();
				System.out.println(result);
				//socket.close();
			}

		} catch (IOException e) {
			// TODO: handle exception
		}

	}
}
