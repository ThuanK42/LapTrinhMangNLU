package quanlyxemphim;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Clientt {
	private static final String localHost = "127.0.0.1";
	private static final int port = 9999;

	public static void main(String[] args) {

		try {

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

				System.out.println("Lenh dung: xemphimchieu month");
				System.out.println("Lenh dung: xemsocho maphim");
				System.out.println("Lenh dung: xemdongia maphim");
				System.out.println("Lenh dung: xemthanhtien makh");
				System.out.println("Lenh dung: thanhtoan makh sotien");

				String userComm = userInput.readLine();// doc tung dong lenh
				out.writeUTF(userComm);// push len hang doi
				out.flush();// day qua sv
				// nhan ket qua tra ve
				String result = in.readUTF();
				System.out.println(result);
				// socket.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
