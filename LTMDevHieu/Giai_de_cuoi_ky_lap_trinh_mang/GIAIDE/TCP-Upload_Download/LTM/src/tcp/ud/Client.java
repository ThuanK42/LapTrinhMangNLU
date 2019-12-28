package tcp.ud;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Client {
	private Socket socket;
	private DataInputStream netIn;
	private DataOutputStream netOut;
	private BufferedReader userIn;
	private String clientDir;
	
	private static final int PORT = 12345;
	private static final String HOST = "127.0.0.1";
	
	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.processClient();
	}

	private void processClient() throws Exception {
		socket = new Socket(HOST, PORT);
		netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		userIn = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		while(true) {
			line = userIn.readLine();
			processLine(line);
			if("QUIT".equalsIgnoreCase(line)) break;
		}
		netIn.close();
		netOut.flush();
		netOut.close();
		userIn.close();
		socket.close();
	}

	private void processLine(String line) throws Exception {
		StringTokenizer st = new StringTokenizer(line);
		String command = st.nextToken();
		String sf, df;
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
			break;
		}
		netOut.flush();
	}

	private void download(String df) throws IOException {
		File file = new File(clientDir + File.separator + df);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		
		long size = netIn.readLong();
		int byteRead, byteMustRead;
		long remain = size;
		byte[] buff = new byte[10*1024];
		
		while(remain > 0) {
			byteMustRead = buff.length > remain ? (int) remain : buff.length;
			byteRead = netIn.read(buff, 0, byteMustRead);
			bos.write(buff, 0 , byteRead);
			remain -=byteRead;
		}
		bos.close();
	}

	private void upload(String sf) throws Exception {
		File sfClient = new File(clientDir + File.separator + sf);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sfClient));
		
		netOut.writeLong(sfClient.length());
		byte[] buff = new byte[10*1024];
		int data;
		while((data = bis.read(buff)) != -1) {
			netOut.write(buff, 0 , data);
		}
		netOut.flush();
		bis.close();
	}

}
