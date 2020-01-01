package exercise26;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.StringTokenizer;

public class Client {
	public static void main(String[] args) throws NotBoundException, IOException {
		Registry reg = LocateRegistry.getRegistry("localhost", 12345);
		IStudent stub = (IStudent) reg.lookup("FindStudent");
		System.out.println("Enter command here:");
		
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String cmd = userIn.readLine();
//			StringTokenizer st = new StringTokenizer(cmd);
//			String command = st.nextToken();
//			String name = st.nextToken();
			String[] arrStr = cmd.split(" ");
			String command = arrStr[0];
			if (command.equalsIgnoreCase("quit")) {
				break;
			}
			String name = arrStr[1];
			if (command.equalsIgnoreCase("findbyname")) {
				String nameRes = stub.findByName(name);
				System.out.println(nameRes);
			}
			if (command.equalsIgnoreCase("findbyage")) {
				String nameRes = stub.findByAge(Double.parseDouble(name));
				System.out.println(nameRes);
			}
			if (command.equalsIgnoreCase("findbyscore")) {
				String result = stub.findByLessScore(Double.parseDouble(name));
				System.out.println(result);
			}
		}
		userIn.close();
		
	}
}
