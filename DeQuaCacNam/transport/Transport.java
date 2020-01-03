package TCP.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class Transport extends Thread {
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	public String saveServerDirDefault = "upload";
	public String saveClientDirDefault = "download";
	String message = "";

	public Transport(Socket s) throws IOException {
		this.s = s;
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
	}

	@Override
	public void run() {
		try {
			dos.writeUTF("Welcome! \n");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		while (true) {
			try {
				String request = dis.readUTF();
				if ("QUIT".equalsIgnoreCase(request)) {
					message="Thanks! Goodbye and see you again...";
					dos.writeUTF(message);
					dos.flush();
					break;
				}

				StringTokenizer st = new StringTokenizer(request, " ");
				String keyCommand = st.nextToken().toUpperCase();
				switch (keyCommand) {
				case "SEND":
					String sourceFile = st.nextToken();
					String saveFileWithName = st.nextToken();
					if(st.hasMoreTokens()){
						dos.writeUTF("-11");dos.flush();break;
					}
					dos.writeUTF(saveClientDirDefault);dos.flush();
					long fileSize = dis.readLong();
					fileReceive(saveFileWithName, fileSize);
					message = "Receive success!";
					break;
				case "GET":
					String sfName = st.nextToken();
					String dfName = st.nextToken();
					if(st.hasMoreTokens()){
						dos.writeUTF("-11");dos.flush();break;
					}
					File sf = new File(saveServerDirDefault+File.separator+sfName);
					if(!sf.exists()) {
						dos.writeUTF("-1");dos.flush();break;
					}
					dos.writeUTF(saveClientDirDefault);dos.flush();
					fileSend(sf);
					message = "Send success!";
					break;
				case "SET_SERVER_DIR":
					saveServerDirDefault=st.nextToken();
					if(st.hasMoreTokens()){
						dos.writeUTF("-11");dos.flush();break;
					}
					message = "Changed save directory from C://dest to "+ saveServerDirDefault+" success!";
					break;
				case "SET_CLIENT_DIR":
					saveClientDirDefault=st.nextToken();
//					if(st.hasMoreTokens()){
//						dos.writeUTF("-11");dos.flush();break;
//					}
					message = "Changed save directory from C://source to "+ saveClientDirDefault+" success!";
					break;
				default:
					message="Key word command not exactly";
					break;
				}

				dos.writeUTF(message);
				dos.flush();

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Loi");
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	public void fileReceive(String fileName, long fileSize) throws IOException {
		File f = new File(saveServerDirDefault + File.separator + fileName);
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(f));
			dos.writeInt(0);
			dos.flush();
			for (int i = 0; i < fileSize; i++) {
				bos.write(dis.read());
				bos.flush();
			}
			bos.close();
		} catch (IOException e) {
		}
	}

	public void fileSend(File sf) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));
		dos.writeLong(sf.length());dos.flush();
		int data;
		while((data=bis.read())!=-1) {
			dos.write(data);dos.flush();
		}
		bis.close();
	}
}
