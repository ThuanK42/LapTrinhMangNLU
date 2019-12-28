package rmi.client;

import java.io.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

import rmi.server.IProduct;

public class Client {
	BufferedReader userIn;
	boolean isLogin = false;
	IProduct iProduct;

	private void start() throws NotBoundException, IOException {
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1212);
		iProduct = (IProduct) registry.lookup("Product");
		userIn = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(iProduct.getWelcome());
		isLogin = checkLogin();

		String line, command, ten_san_pham;
		int idsp, so_luong;
		double gia_ban;
		StringTokenizer st;
		String result = "";
		int count = 0;
		while (isLogin) {
			line = userIn.readLine();
			if ("QUIT".equalsIgnoreCase(line))
				break;
			st = new StringTokenizer(line);
			command = st.nextToken();
			switch (command) {
			case "ADD":
				idsp = Integer.parseInt(st.nextToken());
				ten_san_pham = st.nextToken();
				so_luong = Integer.parseInt(st.nextToken());
				gia_ban = Double.parseDouble(st.nextToken());
				if (iProduct.add(idsp, ten_san_pham, so_luong, gia_ban)) {
					result = "OK";
				} else {
					result = "ERROR";
				}
				break;
			case "REMOVE":
				while (st.hasMoreTokens()) {
					idsp = Integer.parseInt(st.nextToken());
					if (iProduct.remove(idsp)) {
						count += 1;
					} else {
						count += 0;
					}
				}
				result = count + "";
				break;
			case "EDIT":
				idsp = Integer.parseInt(st.nextToken());
				ten_san_pham = st.nextToken();
				so_luong = Integer.parseInt(st.nextToken());
				gia_ban = Double.parseDouble(st.nextToken());
				if (iProduct.edit(idsp, ten_san_pham, so_luong, gia_ban)) {
					result = "OK";
				} else {
					result = "CAN NOT UPDATE";
				}
				break;
			case "VIEW":
				ten_san_pham = st.nextToken();
				result = iProduct.view(ten_san_pham);
				break;
			default:
				result = "command error";
				break;
			}
			System.out.println(result);

		}
		userIn.close();

	}

	private boolean checkLogin() throws IOException {

		String line, command, pass, username;
		String result = "";
		StringTokenizer st;
		while (true) {
			line = userIn.readLine();
			if ("EXIT".equalsIgnoreCase(line))
				return false;
			st = new StringTokenizer(line);
			command = st.nextToken();
			switch (command) {
			case "USER":
				username = st.nextToken();
				if (iProduct.checkUserName(username)) {
					result = "OK";
				} else {
					result = "FALSE";
				}
				break;
			case "PASS":
				try {
					pass = st.nextToken();
					if (iProduct.checkPass(pass)) {
						result = "OK";
						System.out.println(result);
						return true;
					} else {
						result = "FALSE";
					}
				} catch (RemoteException e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				result = "command error";
				break;
			}
			System.out.println(result);
		}
	}

	public static void main(String[] args) throws NotBoundException, IOException {
		Client client = new Client();
		client.start();
	}
}
