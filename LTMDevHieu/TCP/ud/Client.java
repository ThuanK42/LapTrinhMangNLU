package tcp.ud;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Client {
	Socket socket;
	DataInputStream netIn;
	DataOutputStream netOut;
	BufferedReader userIn;
	String clientDir;

	public static void main(String[] args) throws IOException {
		Client client = new Client();
		client.start();
	}

	public void start() throws IOException {
		socket = new Socket("127.0.0.1", 12345);
		netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		userIn = new BufferedReader(new InputStreamReader(System.in));

		String line;
		while (true) {
			line = userIn.readLine();
			processLine(line);
			if("QUIT".equalsIgnoreCase(line)) {
				break;
			}
			
		}
		netIn.close();
		netOut.flush();
		netOut.close();
		userIn.close();
		socket.close();
	}

	private void processLine(String line) throws IOException {
		StringTokenizer st = new StringTokenizer(line);
		String command = st.nextToken();
		String df, sf;
		switch (command) {
		case "SET_SERVER_DIR":
			netOut.writeUTF(command + " " + st.nextToken());
			break;
		case "SET_CLIENT_DIR":
			clientDir = st.nextToken();
			break;
		case "SEND":
			sf = st.nextToken();
			df = st.nextToken();
			netOut.writeUTF(command + " " + df);
			upload(sf);
			break;
		case "GET":
			sf = st.nextToken();
			df = st.nextToken();
			netOut.writeUTF(command + " " + sf);
			netOut.flush();
			download(df);
			break;
		case "QUIT":
			netOut.writeUTF(line);
			break;
		default:
			System.out.println("Sai cuu phap");
			break;
		}
		netOut.flush();
	}

	private void download(String df) throws IOException {
		File file = new File(clientDir + File.separator + df);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		
		long size = netIn.readLong();
		int byteRead, byteMuteRead;
		byte[] buff = new byte[10*1024];
		long remain = size;
		while(remain > 0) {
			byteMuteRead = buff.length > remain ? (int)remain : buff.length;
			byteRead = netIn.read(buff, 0, byteMuteRead);
			bos.write(buff, 0, byteRead);
			bos.flush();
			remain -= byteRead;
		}
		bos.close();
	}

	private void upload(String sf) throws IOException {
		File file = new File(clientDir + File.separator + sf);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		netOut.writeLong(file.length());
		byte[] buff = new byte[10*1024];
		int data;
		while((data = bis.read(buff)) != -1) {
			netOut.write(buff, 0, data);
		}
		netOut.flush();
		bis.close();
	}

}
