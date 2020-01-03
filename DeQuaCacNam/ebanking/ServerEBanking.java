package TCP.ebanking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEBanking {
	public static final int PORT = 12345;

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(PORT);
		while(true){
			Socket s = ss.accept();
			Thread bankService = new BankServices(s);
			bankService.start();
		}
	}
}
