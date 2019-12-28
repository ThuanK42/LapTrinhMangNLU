package RMI_POP3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.StringTokenizer;

public class Client {
	IRMI i;
	String lastuserName = null;
	User u = null;

	public Client() throws RemoteException, NotBoundException {
		Registry re = LocateRegistry.getRegistry("127.0.0.1", 2345);
		i = (IRMI) re.lookup("ok");
		i = new MyRMI();

		System.out.println("WELCOME");

		User user = Login();
		if (user != null) {
			System.out.println("Hi," + user.getUsername());
			process();
		}

	}

	public void process() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				String request = br.readLine().trim();
				if (request.equalsIgnoreCase("QUIT")) {
					System.out.println("Bye");
					br.close();
					break;
				}
				StringTokenizer st = new StringTokenizer(request, " ");
				String command = st.nextToken().toUpperCase();
				switch (command) {
				case "SEND":
					String sfile = st.nextToken();
					String dfile = st.nextToken();
					i.createDest(dfile);
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(sfile)));
					int a;
					byte[] b = new byte[1024];
					while ((a = bis.read(b)) != -1) {
						i.writeData(b, a);
					}
					i.closeDest();
					bis.close();
					System.out.println("up thành công");
					break;
				case "GET":
					String sf = st.nextToken();
					String df = st.nextToken();

					i.openSource(sf);
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(df)));
					byte[] b1;
					while ((b1 = i.readData()) != null) {
						bos.write(b1);
					}
					i.closeSource();

					bos.close();
					System.out.println("tai thành công");
					break;
				default:
					System.out.println("không tìm thấy");
					break;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public User Login() {
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				String request = br.readLine().trim();
				if (request.equalsIgnoreCase("QUIT")) {
					br.close();
					return null;

				}

				int space = request.indexOf(" ");

				if (space < 4) {
					if (request.equalsIgnoreCase("USER")) {
						System.out.println("command not enough");
					} else if (request.equalsIgnoreCase("PASS")) {
						System.out.println("comamnd not enough");
					} else {
						System.out.println("command not found");

					}
					continue;
				}

				StringTokenizer st = new StringTokenizer(request, " ");
				String command = st.nextToken().toUpperCase();
				switch (command) {
				case "USER":
					String username = st.nextToken();
					boolean check = i.checkExistUserName(username);

					if (check) {
						System.out.println("OK");
						lastuserName = username;
					}

					else {
						System.out.println("FALSE");
						lastuserName = null;
					}

					break;

				case "PASS":
					String pass = st.nextToken();
					if (lastuserName == null) {
						System.out.println("FALSE");
					} else {
						boolean login = i.login(lastuserName, pass);
						if (login) {
							u = i.get(lastuserName);
							return u;
						} else {
							System.out.println("FALSE");
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
			}

		}
	}

	public static void main(String[] args) throws RemoteException, NotBoundException {
		new Client();
	}
}