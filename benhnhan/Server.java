package benhnhan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
	static ProcessServer ps = new ProcessServer();

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		ServerSocket ss = new ServerSocket(7777);
		
		while (true) {
			System.out.println("wait....");
			Socket s = ss.accept();
			System.out.println("Connect....");
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String readClient = dis.readUTF();
			System.out.println("Client send......." + readClient);

			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			if (readClient.equalsIgnoreCase("EXIT")) {
				dos.writeUTF("Disconnect");
				dos.flush();
				s.close();
				ss.close();
			} else {
				String[] any = readClient.split("\t");
				String key = any[0];
				String value = any[1];

				if (key.equalsIgnoreCase("XEMTIENVIENPHI")) {
					String result = ps.getTienVienPhi(value);
					if (result == null) {
						dos.writeUTF("not name");
						dos.flush();
					} else {
						dos.writeUTF(result);
						dos.flush();
					}
				} else if (key.equalsIgnoreCase("XEMLOAIBENH")) {
					String result = ps.getLoaiBenh(value);
					if (result == null) {
						dos.writeUTF("not name");
						dos.flush();
					} else {
						dos.writeUTF(result);
						dos.flush();
					}
				} else {
					dos.writeUTF("comment error");
					dos.flush();
				}

			}
		}

	}

}
