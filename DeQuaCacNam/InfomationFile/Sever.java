package TCP.InfomationFile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever {
	public static final int PORT =55555;
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(PORT);
		while(true){
			Socket s = ss.accept();
			Thread pif = new ProvidedInfomationFile(s);
			pif.start();
		}
	}
}
