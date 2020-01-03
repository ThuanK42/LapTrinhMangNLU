package TCP.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class ClientTransport {

	public static void main(String[] args) throws IOException {
		int errorCode = -1;
		Socket sk = new Socket("127.0.0.1", ServerTransport.PORT);
		DataInputStream dis = new DataInputStream(sk.getInputStream());
		DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
		System.out.println(dis.readUTF());
		while (true) {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			String command = bf.readLine();
			dos.writeUTF(command);
			dos.flush();

			StringTokenizer st = new StringTokenizer(command, " ");
			String key = st.nextToken().toUpperCase();
			switch (key) {
			case "SEND":
				String pathDirectoryUploadAtClient = dis.readUTF();
				if (pathDirectoryUploadAtClient.equals("-11")) {
					System.out.println("Too many param!!!");
					break;
				}
				String sf = st.nextToken();
				File fileSource = new File(pathDirectoryUploadAtClient + File.separator + sf);
				if (!fileSource.exists()) {
					System.out.println("Source file name not exists!!!");
					break;
				}
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileSource));
				dos.writeLong(fileSource.length());
				dos.flush();
				errorCode = dis.readInt();
				if (errorCode == 0) {
					int data;
					while ((data = bis.read()) != -1) {
						dos.write(data);
						dos.flush();
					}
					bis.close();
				}
				break;
			case "GET":
				String pathDirectorySaveAtClient = dis.readUTF();
				if (pathDirectorySaveAtClient.equals("-1")) {
					System.out.println("Source file name not exists!!!");
					break;
				} else if (pathDirectorySaveAtClient.equals("-11")) {
					System.out.println("Too many param!!!");
					break;
				}
				String sourceFile = st.nextToken();
				String saveFileWithName = st.nextToken();
				File df = new File(pathDirectorySaveAtClient + File.separator + saveFileWithName);
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
				long fileSize = dis.readLong();
				for (int i = 0; i < fileSize; i++) {
					bos.write(dis.read());
					bos.flush();
				}
				bos.close();
				break;
			case "SET_CLIENT_DIR":
//				String testError = dis.readUTF();
//				if (testError.equals("-11")) {
//					System.out.println("Too many param!!!");
//					break;
//				}
				break;
			case "SET_SERVER_DIR":
//				String testErrorServer = dis.readUTF();
//				if (testErrorServer.equals("-11")) {
//					System.out.println("Too many param!!!");
//					break;
//				}
				break;
			default:
				break;
			}

			System.out.println(dis.readUTF());
		}
	}
}
