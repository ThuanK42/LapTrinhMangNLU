package tcp2con;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Created by Aragon on 8/4/2017.
 */
public class Server {
	public static final String source="E:\\s1";
	ServerSocket ss;
	Socket socket;
	int port = 12345;
	BufferedReader in;
	PrintWriter out;
	String commend, fileName;

	public Server() throws IOException {
		createConnet();
		createStream();
		sayHello();
		while (true) {
			sendCommend();
			receiptCommend();
			out.println(commend);
			System.out.println(commend);
			out.flush();
			if (commend.equalsIgnoreCase("Download"))
				download();
			else
				;
		}
	}

	private void download() throws IOException {
		ServerSocket ss1= new ServerSocket(7777);
		Socket socket1= ss1.accept();
		DataOutputStream dos= new DataOutputStream(socket1.getOutputStream());
		DataInputStream din= new DataInputStream(socket1.getInputStream());
		BufferedInputStream fin=new BufferedInputStream(new FileInputStream(source+"\\"+fileName));
		dos.writeLong(new File(source+"\\"+fileName).length());
		dos.flush();
		System.out.println(new File(source+"\\"+fileName).length());
		dos.writeUTF(fileName);
		dos.flush();
		byte[]bs= new byte[1024];
		int a=0;
		while((a=fin.read(bs))!=-1){
			dos.write(bs, 0, a);
			dos.flush();
		}
		dos.close();
		fin.close();
		System.out.println("File đã gửi");
		socket1.close();
	}

	private void exit() {
		System.exit(1);
	}

	public void createConnet() throws IOException {
		ss = new ServerSocket(port);
		System.out.println("Dang che ket noi");
		socket = ss.accept();
		System.out.println("Kết nối thành công");
	}

	public void createStream() throws IOException {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
	}

	public void sayHello() {
		out.println("Chào mừng bạn đến với ứng dung");
		out.flush();
	}

	public void sendCommend() {
		out.println("Bạn hãy chọn lệnh thực hiện");
		out.println("Download\tFile_name");
		out.println("Upload\tFile_name");
		out.println("Thoat chương trình: thoat");
		out.println("exit");
		out.flush();
	}

	public boolean receiptCommend() throws IOException {
		commend = in.readLine();
		StringTokenizer token = new StringTokenizer(commend, "\t");
		String s = token.nextToken();
		if (s.equalsIgnoreCase("Download") || commend.equalsIgnoreCase("Upload")) {
			fileName = token.nextToken();
			commend=s;
			out.println("lệnh đúng");
			out.flush();
			out.println("1");
			out.flush();
			return true;
		} else {
			out.println("Lệnh sai");
			out.flush();
			out.println("0");
			out.flush();
			return false;
		}
	}

	public static void main(String[] args) {
		try {
			Server s = new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
