package manSERVER2014_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.StringTokenizer;

public class client {

	MySanPham my;
	User u;

	public client() throws RemoteException, NotBoundException {

		Registry re = LocateRegistry.getRegistry("127.0.0.1", 1221);
		my = (MySanPham) re.lookup("sp");
		my = new SanPhamDAOImpt();
		System.out.println("WELCOME");
		User user = login();
		if (user != null) {
			System.out.println("Dang nhap thanh cong");
			System.out.println("Xin chao: " + user.getUsername());
		}

	}

	public User login() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String lastuserName = null;
		User user = null;
		while (true) {
			try {
				String request = br.readLine().trim();
				if (request.equalsIgnoreCase("QUIT")) {
					return null;
				}
				int space = request.indexOf("\t");
				if (space < 4) {
					if (request.equalsIgnoreCase("USER")) {
						System.out.println("command not enough");
					} else if (request.equalsIgnoreCase("PASS")) {
						System.out.println("command not enough");
					} else {
						System.out.println("not found");
					}
					continue;
				}

				StringTokenizer st = new StringTokenizer(request, "\t");
				String command = st.nextToken().toUpperCase();
				switch (command) {
				case "USER":
					String username = st.nextToken();
					boolean check = my.checkExistUserName(username);
					if (check) {
						System.out.println("OK");
						lastuserName = username;
					} else {
						System.out.println("ERROR");
						lastuserName = null;
					}
					break;

				case "PASS":
					String pass = st.nextToken();

					boolean login = my.login(lastuserName, pass);
					if (lastuserName == null) {
						System.out.println("ERROR");

					}

					else {
						if (login) {
							System.out.println("OK");

							user = my.get(lastuserName);
							return user;
						} else {
							System.out.println("ERROR");
						}
					}
					break;
				default:
					System.out.println("command not found");
					break;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}

	}

	public static void main(String[] args) throws RemoteException, NotBoundException {
		new client();
	}
}