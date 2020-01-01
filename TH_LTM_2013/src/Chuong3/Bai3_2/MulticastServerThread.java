package Chuong3.Bai3_2;

import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastServerThread extends QuoteServerThread {
	private long FIVE_SECONDS = 5000;

	public MulticastServerThread() throws IOException {
		super("MulticastServerThread");
	}

	public void run() {
		while (moreQuotes) {
			try {
				byte[] buf = new byte[256];

				// construct quote
				String dString = null;
				if (in == null)
					dString = new Date().toString();
				else
					dString = getNextQuote();
				buf = dString.getBytes();

				// send it
				InetAddress group = InetAddress.getByName("230.0.0.1");
				DatagramPacket packet = new DatagramPacket(buf, buf.length,
						group, 4445);
				socket.send(packet);
				System.out.println("Got quotes: " + new String(buf));
				System.out.println("Send quotes");
				// sleep for a while
				try {
					sleep(FIVE_SECONDS);
				} catch (InterruptedException e) {
				}
			} catch (IOException e) {
				e.printStackTrace();
				moreQuotes = false;
			}
		}
		socket.close();
	}
}
