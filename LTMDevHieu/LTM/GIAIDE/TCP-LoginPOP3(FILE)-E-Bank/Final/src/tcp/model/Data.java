package tcp.model;

import java.io.*;
import java.util.*;

public class Data {
	public static ArrayList<User> getData() throws IOException{
		ArrayList<User> res = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\De03\\Final\\user.txt"), "UTF-16"));
		String line;
		StringTokenizer st;
		while((line = br.readLine()) != null) {
			st = new StringTokenizer(line);
			res.add(new User(st.nextToken(), st.nextToken(), st.nextToken(), Double.parseDouble(st.nextToken())));
		}
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		for(User u: getData()) {
			System.out.println(u);
		}
	}

}
