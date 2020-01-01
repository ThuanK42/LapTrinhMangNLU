package tcp.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

import tcp.model.Data;
import tcp.model.User;

public class ProcessServer extends Thread {
	Socket socket;
	DataInputStream netIn;
	DataOutputStream netOut;
	User user;

	public ProcessServer(Socket socket) throws IOException {
		this.socket = socket;
		user = null;
		netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		netOut.writeUTF("Welcom to NLU e-bank");
		netOut.flush();
		
	}
	
	@Override
	public void run() {
		try {
			String line, command, username, pass;
			StringTokenizer st;
			String so_tien, tk;
			double tien;
			while(true) {
				line = netIn.readUTF();
				if("QUIT".equalsIgnoreCase(line)) break;
				st = new StringTokenizer(line);
				command = st.nextToken();
				switch (command) {
				case "TEN":
					username = st.nextToken();
					if(checkUsername(username)) {
						netOut.writeUTF("OK");
					}else{
						netOut.writeUTF("FALSE");
					}
					break;
				case "MATKHAU":
					pass = st.nextToken();
					if(user == null) {
						netOut.writeUTF("HAVE TO ENTER USERNAME FIRST");
					}else {
						if(checkPass(pass)) {
							netOut.writeUTF("OK");
						}else{
							netOut.writeUTF("FALSE");
						}					
					}
					break;
				case "GUI":
					so_tien = st.nextToken();
					tien = Double.parseDouble(so_tien);
					netOut.writeUTF("GUI SUCCES, OLD: " + user.getSoTien() + ", NEW: " +  guiTien(tien));
					break;
				case "LAY":
					so_tien = st.nextToken();
					tien = Double.parseDouble(so_tien);
					netOut.writeUTF("LAY SUCCES, OLD: " + user.getSoTien() + ", NEW: " +  layTien(tien));
					break;
				case "CHUYEN":
					tk = st.nextToken();
					so_tien = st.nextToken();
					tien = Double.parseDouble(so_tien);
					netOut.writeUTF("CHUYEN SUCCES, OLD: " + user.getSoTien() + ", NEW: " +  chuyenTien(tk, tien));
					break;

				default:
					break;
				}
				netOut.flush();
			}
		} catch (IOException e) {
		}
	}
	
	

	private double chuyenTien(String tk, double tien) throws IOException {
		user.setSoTien(user.getSoTien() -tien);
		for(User u: Data.getData()) {
			if(u.getSoTaiKhoan().equals(tk)) {
				u.setSoTien(u.getSoTien() + tien);
			}
		}
		return user.getSoTien();
	}

	private double layTien(double tien) {
		user.setSoTien(user.getSoTien() -tien);
		return user.getSoTien();
	}

	private double guiTien(double tien) {
		user.setSoTien(user.getSoTien() + tien);
		return user.getSoTien();
	}

	private boolean checkPass(String pass) throws IOException {
		for(User u: Data.getData()) {
			if(u.getMatKhau().equals(pass) && u.getTen().equals(user.getTen())) {
				user.setMatKhau(pass);
				return true;
			}
		}
		return false;
	}

	boolean checkUsername(String username) throws IOException {
		for(User u: Data.getData()) {
			if(u.getTen().equals(username)) {
				user = new User(username, null, u.getSoTaiKhoan(), u.getSoTien());
				return true;
			}
		}
		return false;
	}

}
