package rmi.ud;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Client {
	BufferedReader userIn;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	boolean isLogin;
	IFile file;

	
	private void start() throws IOException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12345);
		file = (IFile) registry.lookup("File");

		userIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("WELCOME");

		isLogin = checkLogin();
		System.out.println("login is success");

		
		while (isLogin) {
			String line;
			StringTokenizer st;
			String command;
			String result = "";
			String sf, df;
			byte[] buff;
			try {
				line = userIn.readLine();
				if ("QUIT".equalsIgnoreCase(line))
					break;
				st = new StringTokenizer(line);
				command = st.nextToken();
				switch (command) {
				case "GET":
					try {
						sf = st.nextToken();
						df = st.nextToken();
						
						file.openSource(sf);
						bos = new BufferedOutputStream(new FileOutputStream(df));
						while ((buff = file.readData()) != null) {
							bos.write(buff);
						}
						file.closeSource();
						bos.close();
					} catch (NoSuchElementException e) {
						result = "command error";
					} catch (FileNotFoundException e) {
						result = "source file can not found";
					}
					break;
				case "SEND":
					try {
						sf = st.nextToken();
						df = st.nextToken();

						bis = new BufferedInputStream(new FileInputStream(sf));
						file.createDest(df);
						buff = new byte[1024 * 10];
						int data;
						while ((data = bis.read(buff)) != -1) {
							file.writeData(buff, data);
						}

						file.closeDest();
						bis.close();
					} catch (NoSuchElementException e) {
						result = "command error";
					} catch (FileNotFoundException e) {
						result = "source file can not found";
					}
					break;
				default:
					break;
				}
				System.out.println(result);
				
			} catch (RemoteException e) {
				System.out.println(e.getMessage());
			}

		}
		userIn.close();
	}

	private boolean checkLogin() throws IOException {
		while (true) {
			String line;
			StringTokenizer st;
			String command;
			String username, password;
			String result = "";
			try {
				line = userIn.readLine();
				if ("QUIT".equals(line))
					return false;

				st = new StringTokenizer(line);
				command = st.nextToken();
				switch (command) {
				case "TEN":
					try {
						username = st.nextToken();
						if (file.checkUserName(username)) {
							result = "username is ok";
						} else {
							result = "username is not ok";
						}
					} catch (NoSuchElementException e) {
						result = "command is error";
					}
					break;
				case "MATKHAU":
					try {
						password = st.nextToken();
						if (file.checkPass(password)) {
							return true;
						} else {
							result = "password is not ok";
						}
					} catch (NoSuchElementException e) {
						result = "command is error";
					}
					break;
				default:
					result = "you have to login first";
					break;
				}
				System.out.println(result);
			} catch (RemoteException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) throws IOException, NotBoundException {
		Client client = new Client();
		client.start();
	}
}
