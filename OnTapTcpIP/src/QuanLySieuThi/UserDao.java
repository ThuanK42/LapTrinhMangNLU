package QuanLySieuThi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UserDao {

	User user = null;
	static List<User> listUser = new ArrayList<>();
	static String fileUser = "src\\QuanLySieuThi\\User.txt";

	public UserDao() {
		napDuLieu();
	}

	public void napDuLieu() {
		File file = new File(fileUser);
		if (file.isDirectory() || !file.exists() || !file.isFile()) {
			return;
		} else {
			try {
				List<String> listDuLieuBanDau = new ArrayList<>();
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line;
				while ((line = br.readLine()) != null) {
					listDuLieuBanDau.add(line);
				}
				listDuLieuBanDau.remove(0);// loai bo dong username. passs
				for (String str : listDuLieuBanDau) {
					StringTokenizer token = new StringTokenizer(str, "\t");
					User us = new User(token.nextToken(), token.nextToken());
					listUser.add(us);
				}
				br.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int checkUser(String name) {
		for (User user : listUser) {
			if (user.getUsername().equalsIgnoreCase(name)) {
				this.user = new User(name, "");
				return 1;
			}
		}
		this.user = null;
		return 0;
	}

	public int checkLogin(String passs) {
		if (this.user == null) {
			return 2;
		}
		for (User user : listUser) {
			if (user.getPassword().equalsIgnoreCase(passs)
					&& user.getUsername().equalsIgnoreCase(this.user.getUsername())) {
				this.user.setPassword(passs);
				return 1;
			}
		}
		return 0;
	}

}
