package socket;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class UDPClientStudent {
	final static int port = 5555;
	final static String host = "localhost";
	private static DatagramSocket clientSocket;

	public static void sendRequest() {
		while(true){
		try {
			BufferedReader inFromUser = new BufferedReader(
					new InputStreamReader(System.in));
			System.out.println("Enter infomations: findByName name");
			clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName(host);
			byte[] sendData = new byte[1024*10];
			byte[] receiveData = new byte[1024*10];
			String sentence = inFromUser.readLine();
			sendData = sentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, port);
			clientSocket.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receiveData, 0, receivePacket.getLength());
			if (modifiedSentence.equalsIgnoreCase("Exit")) {
				System.out.println("Closing this connection : " + clientSocket);
				System.out.println("[CLIENT] Goodbye!");
				clientSocket.close();
				return;
				
			} else if(modifiedSentence.equalsIgnoreCase("Incorrect")){
				System.out.println("FROM SERVER:		" + modifiedSentence);
				
			}else{
			System.out.println("FROM SERVER:		" + modifiedSentence);
			}
			//clientSocket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}
	public static void main(String[] args) {
		sendRequest() ;
	}

}
