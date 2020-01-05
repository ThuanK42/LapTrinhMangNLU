package NganHangNLU;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Client {

	Socket socket;
	DataInputStream dataInputStream;
	DataOutputStream dataOutputStream;
	String lenh;
	BufferedReader br;

	public Client() throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 12345);
		br = new BufferedReader(new InputStreamReader(System.in));
		dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}

	private void run() {
		try {
		//	System.out.println(dataInputStream.readUTF());
			while (true) {

				lenh = br.readLine();
				if ("QUIT".equalsIgnoreCase(lenh)) {
					System.out.println("Thank you.See you later");
					break;
				}

				phanTichLenh(lenh);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void phanTichLenh(String lenh2) {
		StringTokenizer token = new StringTokenizer(lenh2, "\t");
		while (token.hasMoreTokens()) {
			String command = token.nextToken();

			switch (command) {
			case "TEN":

				try {
					dataOutputStream.writeUTF(lenh2);
					dataOutputStream.flush();
					System.out.println(dataInputStream.readUTF());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "MATKHAU":
				try {
					dataOutputStream.writeUTF(lenh2);
					dataOutputStream.flush();
					System.out.println(dataInputStream.readUTF());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "GUI":
				try {
					dataOutputStream.writeUTF(lenh2);
					dataOutputStream.flush();
					System.out.println(dataInputStream.readUTF());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "LAY":
				try {
					dataOutputStream.writeUTF(lenh2);
					dataOutputStream.flush();
					System.out.println(dataInputStream.readUTF());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "CHUYEN":
				try {
					dataOutputStream.writeUTF(lenh2);
					dataOutputStream.flush();
					System.out.println(dataInputStream.readUTF());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			default:

				break;
			}

		}

	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client();
		client.run();
	}
}
