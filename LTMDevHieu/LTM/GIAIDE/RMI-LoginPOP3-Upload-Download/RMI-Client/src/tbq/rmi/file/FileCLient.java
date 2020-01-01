package tbq.rmi.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class FileCLient {
	BufferedReader userIn;
	boolean isLogin;
	BufferedInputStream bis;
	BufferedOutputStream bos;

	public void start() throws IOException, NotBoundException {

		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12345);
		IFile file = (IFile) registry.lookup("File");
		StringTokenizer st;
		userIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		System.out.println("Welcome");

		while (true) {
			try {
				line = userIn.readLine();
				if ("QUIT".equalsIgnoreCase(line))
					break;
				if (!isLogin) {
					st = new StringTokenizer(line);
					String command = st.nextToken();

					switch (command) {
					case "TEN":
						try {
							String name = st.nextToken();
							if(file.findName(name)) {
								System.out.println("Ten dang nhap dung");
							}else{
								System.out.println("Ten dang nhap sai");
							}
						} catch (NoSuchElementException e) {
							System.out.println("Sai cu phap");
						}
						break;
					case "MATKHAU":
						try {
							String pass = st.nextToken();
							if(file.checkPass(pass)) {
								System.out.println("Dang nhap thanh cong");
								isLogin = true;
							}else{
								System.out.println("Mat khau sai");
							};
						} catch (NoSuchElementException e) {
							System.out.println("Sai cu phap");
						}
						break;

					default:
						System.out.println("Cu phap sai");
						break;
					}
				}else {
					st = new StringTokenizer(line);
					String command = st.nextToken();
					String sf, df;

					switch (command) {
					case "SEND":
						try {
							sf = st.nextToken();
							df = st.nextToken();
							bis = new BufferedInputStream(new FileInputStream(sf));
							file.createDest(df);
							byte[] buff = new byte[10*1024];
							int lenght;
							while((lenght = bis.read(buff)) != -1) {
								file.writeData(buff, lenght);
							}
							bis.close();
							file.closeDest();
						} catch (NoSuchElementException e) {
							System.out.println("Sai cu phap");
						} catch (FileNotFoundException e) {
							System.out.println("khong mo duoc file nguon");
						}
						break;
					case "GET":
						try {
							sf = st.nextToken();
							df = st.nextToken();
							file.openSource(sf);
							bos = new BufferedOutputStream(new FileOutputStream(df));
							byte[] data;
							while((data = file.readData()) != null) {
								bos.write(data, 0, data.length);
							}
							file.closeSource();
							bos.close();
						} catch (NoSuchElementException e) {
							System.out.println("Sai cu phap");
						}
						break;

					default:
						break;
					}
				}
			} catch (RemoteException e) {
				System.out.println(e.getMessage());
			}

		}
		userIn.close();
	}
	
	public static void main(String[] args) throws IOException, NotBoundException {
		FileCLient fc = new FileCLient();
		fc.start();
	}

}
