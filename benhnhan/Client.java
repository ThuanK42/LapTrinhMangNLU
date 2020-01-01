package benhnhan;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws  IOException {
		Socket s = null;
		while (true) {
			s = new Socket("127.0.0.1", 7777);
			System.out.println("Connected.......");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String comm = br.readLine();
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(comm);
			dos.flush();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String readServer = dis.readUTF();
			System.out.println("server send.......");
			if (readServer.equalsIgnoreCase("Disconnect")) {
				s.close();
				break;
			} else {
				System.out.println(readServer);
			}

		}

	}

}
