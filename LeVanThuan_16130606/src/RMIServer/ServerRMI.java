package RMIServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ServerRMI {
	public ServerRMI() throws RemoteException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String result;
		StudentImp studentImp = new StudentImp();
		while (true) {
			String command = sc.nextLine();
			StringTokenizer token = new StringTokenizer(command, "\t");
			String request = token.nextToken();
			switch (request) {
			case "findByName":
				result = studentImp.findByName(token.nextToken());
				System.out.println(result);
				break;
			case "findByUnderScore":
				result = studentImp.findByUnderScore(Double.parseDouble(token.nextToken()));
				System.out.println(result);
				break;
			case "findByUpperScore":
				result = studentImp.findByUpperScore(Double.parseDouble(token.nextToken()));
				System.out.println(result);
				break;
			case "findByUnderAge":
				result = studentImp.findByUnderAge(Integer.valueOf(token.nextToken()));
				System.out.println(result);
				break;
			case "findByUpperAge":
				result = studentImp.findByUpperAge(Integer.valueOf(token.nextToken()));
				System.out.println(result);
				break;
			default:
				System.out.println("Nhap lenh sai vui long nhap lai");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Random r = new Random();
		System.out.println(r.nextInt(24));
	}

}
