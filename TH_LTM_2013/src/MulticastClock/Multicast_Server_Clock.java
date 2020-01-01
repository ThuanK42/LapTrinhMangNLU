package MulticastClock;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Multicast_Server_Clock {
	public static void main(String[] args) {
		new Multicast_Server_Clock().go();
	}

	private String timeToString(int sec, int min, int hour) {
		String data = null;
		if (hour < 10)
			data = "0" + Integer.toString(hour);
		else
			data = Integer.toString(hour);
		if (min < 10)
			data += " : 0" + Integer.toString(min);
		else
			data += " : " + Integer.toString(min);
		if (sec < 10)
			data += " : 0" + Integer.toString(sec);
		else
			data += " : " + Integer.toString(sec);
		return data;
	}

	private void go() {
		try {

			DatagramSocket server = new DatagramSocket();
			InetAddress ip = InetAddress.getByName("225.225.225.225");
			int port, sec, min, hour;
			port = 2222;
			sec = 0;
			min = 0;
			hour = 0;
			byte[] sendData = new byte[256];

			while (true) {
				sendData = timeToString(sec, min, hour).getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, ip, port);
				server.send(sendPacket);
				Thread.sleep(1000);
				sec++;
				if (sec == 60) {
					sec = 0;
					min++;
					if (min == 60) {
						min = 0;
						hour++;
						if (hour == 24)
							hour = 0;
					}
				}
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}