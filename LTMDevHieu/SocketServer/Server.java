import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	final static int port = 4444;
	
	public static void copyFile() throws IOException{
		ServerSocket server = new ServerSocket(port);
		System.out.println("Start connect...");
		System.out.println("Waitting for...");
		
		Socket socket = server.accept();
		System.out.println("Connect success");
		
		// Get information client
		BufferedReader getFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String readCMD = getFromClient.readLine();
		String[] infor = readCMD.split(" ");
		String cmd = infor[0];
		String srcFile = infor[1];
		String desFile = infor[2];
		
		// Proccess data
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile)));
		
		// Send data to client
		PrintWriter sendClient = new PrintWriter(new OutputStreamWriter(new FileOutputStream(desFile)));
		
		String line = "";
		if(cmd.equalsIgnoreCase("Copy")){
			while((line = input.readLine()) != null){
				sendClient.write(line);
				sendClient.write('\n');
			}
		}else{
			sendClient.print("incorrec systax format");
		}
		socket.close();
		input.close();
		sendClient.close();
		getFromClient.close();
	}
	public static void main(String[] args) throws IOException {
		copyFile();
	}
}
