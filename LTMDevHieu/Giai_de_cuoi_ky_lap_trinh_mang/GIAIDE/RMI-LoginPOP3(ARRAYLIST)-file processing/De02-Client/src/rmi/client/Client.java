package rmi.client;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import rmi.server.IProduct;

public class Client {
	BufferedReader userIn;
	IProduct iProduct;
	boolean isLogin = false;

	public static void main(String[] args) throws IOException, NotBoundException {
		Client client = new Client();
		client.start();
	}

	private void start() throws IOException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12345);
		iProduct = (IProduct) registry.lookup("Final");
		userIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(iProduct.getWelcomMessage());
		
		isLogin = checkAccount();
		
		
		String line;
		while (isLogin) {
			line = userIn.readLine();
			if ("QUIT".equals(line))
				break;
			processLine(line);
		}
	}

	private boolean checkAccount() throws IOException {
		String line, command;
		StringTokenizer st;
		while(true) {
			line = userIn.readLine();
			st = new StringTokenizer(line);
			command = st.nextToken();
			String username, pass, result = "", sf;
			try {
				switch (command) {
				case "USERNAME":
					username = st.nextToken();
					if(iProduct.checkUserName(username)) {
						result = "username succes";
					}else {
						result = "username fail";
					}
					break;
				case "PASSWORD":
					pass = st.nextToken();
					if(iProduct.checkPass(pass)) {
						result = "login succes";
						System.out.println(result);
						return true;
					}else {
						result = "pass fail";
					}
					break;
				case "QUIT":
					return false;
				default:
					result = "command error";
					break;
				}
				System.out.println(result);
			} catch (RemoteException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void processLine(String line) {
		StringTokenizer st = new StringTokenizer(line);
		String command = st.nextToken();
		String username, pass, result = "", sf;
		try {
			switch (command) {
			
			case "ADD_FILE":
				sf = st.nextToken();
				File file = new File(sf);
				iProduct.saveDataFile(file);
				result = "Save file done";
				break;
			case "ADD_TEXT":
				while(st.hasMoreTokens()) {
					sf = st.nextToken();
					iProduct.saveDataString(sf);
				}
				result = "Save text dont!";
				break;
			case "GET_NUMS":
				System.out.print(iProduct.getNums());
				break;
			case "GET_SUM":
				System.out.print(iProduct.getSum());
				break;
			case "GET_NUM_LIST":
				ArrayList<Integer> res = iProduct.getNumList();
				for(Integer i:res) {
					System.out.println(i);
				}
				break;
			case "GET_WORDS":
				System.out.print(iProduct.getWords());
				break;
			default:
				result = "command error";
				break;
			}
			System.out.println(result);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
		

	}

}
