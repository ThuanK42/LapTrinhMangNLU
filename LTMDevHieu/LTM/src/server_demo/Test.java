package server_demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {
	public static void info() {
		try {
			InetAddress inet = InetAddress.getLocalHost();
			System.out.println(inet.getHostName());
			System.out.println(inet.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		info();
	}
}
