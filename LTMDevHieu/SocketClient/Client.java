import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
		final static int port = 4444;
		final static String host = "127.0.0.1";
		
		
		public static void sendCopy() throws UnknownHostException, IOException{
				Socket socket = new Socket(host, port);
				System.out.println("Connect to server");
				
				BufferedReader inputCMD = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Format: copy src des");
				String cmd = inputCMD.readLine();
				
				// Send to server
				PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
				pw.write(cmd);
				
				// get data from server
				/*BufferedReader getFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line = "";
				while((line = getFromServer.readLine()) != null){
					pw.write(line);
					pw.write('\n');
				}*/
				pw.close();
				/*getFromServer.close();*/
				socket.close();
		}
		public static void main(String[] args) throws UnknownHostException, IOException {
			sendCopy();
		}
	
}
