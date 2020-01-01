package tcp.ud;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class ServerProcess extends Thread {
	private Socket socket;
	private DataInputStream netIn;
	private DataOutputStream netOut;
	private String serverDir;

	public ServerProcess(Socket socket) throws Exception {
		this.socket = socket;
		netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}

	@Override
	public void run() {
		String line;
		try {
			while (true) {
				line = netIn.readUTF();
				processLine(line);
				if ("QUIT".equalsIgnoreCase(line)) break;
			}
			netIn.close();
			netOut.flush();
			netOut.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processLine(String line) throws IOException {
		StringTokenizer st = new StringTokenizer(line);
		String command = st.nextToken();
		String sf, df;
		switch (command) {
		case "SET_SERVER_DIR":
			serverDir = st.nextToken();
			break;
		case "SEND":
			df = st.nextToken();
			download(df);
			break;
		case "GET":
			sf = st.nextToken();
			upload(sf);
			break;
		default:
			break;
		}

	}

	private void upload(String sf) throws IOException {
		File file = new File(serverDir + File.separator + sf);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		netOut.writeLong(file.length());
		byte[] buff = new byte[10*1024];
		int data;
		
		while((data = bis.read(buff)) != -1) {
			netOut.write(buff, 0, data);
		}
		
		netOut.close();
		bis.close();
	}

	private void download(String df) throws IOException {
		File sfServer = new File(serverDir + File.separator + df);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(sfServer));
		
		long size = netIn.readLong();
		int byteRead, byteMustRead;
		long remain = size;
		byte[] buff = new byte[10*1024];
		
		while(remain > 0) {
			byteMustRead = buff.length > remain ? (int) remain : buff.length;
			byteRead = netIn.read(buff, 0, byteMustRead);
			bos.write(buff, 0 , byteRead);
			remain -= byteRead;
		}
		
		bos.close();
	}

}
