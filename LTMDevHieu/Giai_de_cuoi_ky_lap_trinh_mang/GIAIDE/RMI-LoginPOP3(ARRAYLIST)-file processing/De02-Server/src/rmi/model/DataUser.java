package rmi.model;

import java.util.ArrayList;

public class DataUser {
	
	public static ArrayList<User> getData() {
		ArrayList<User> res = new ArrayList<>();
		res.add(new User("admin", "12345"));
		res.add(new User("system", "123"));
		return res;
	}

}
