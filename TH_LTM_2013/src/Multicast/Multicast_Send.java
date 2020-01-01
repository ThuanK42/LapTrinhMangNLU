package Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Multicast_Send {
	public static void main(String[] args) {
		try {
			DatagramSocket MultiSend = new DatagramSocket();
			InetAddress ip = InetAddress.getByName("225.225.225.225");
			int port = 2222;
			int count = 1;
			byte[] sendData = new byte[256];

			while (true) {
				sendData = ("Hello Multicast " + Integer.toString(count) + " !")
						.getBytes();

				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, ip, port);

				MultiSend.send(sendPacket);

				count++;
				Thread.sleep(1000);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
