package MulticastClock;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Multicast_Client_Clock extends JFrame {
	private JLabel display;

	public Multicast_Client_Clock() {
		super("Clock client");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		setSize(550, 100);
		addItem();
		setVisible(true);
	}

	private void addItem() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		display = new JLabel("00 : 00 : 00");
		display.setFont(new Font("DigifaceWide", Font.BOLD, 70));
		display.setForeground(Color.GREEN);
		add(display);

	}

	public static void main(String[] args) {
		new Multicast_Client_Clock().go();
	}

	private void go() {
		try {
			InetAddress ip = InetAddress.getByName("225.225.225.225");
			int port = 2222;
			byte[] receiveData = new byte[256];
			String temp;

			MulticastSocket client = new MulticastSocket(port);
			client.joinGroup(ip);

			while (true) {
				DatagramPacket receivedPacket = new DatagramPacket(receiveData,
						receiveData.length);
				client.receive(receivedPacket);
				temp = new String(receivedPacket.getData()).trim();
				display.setText(temp);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}