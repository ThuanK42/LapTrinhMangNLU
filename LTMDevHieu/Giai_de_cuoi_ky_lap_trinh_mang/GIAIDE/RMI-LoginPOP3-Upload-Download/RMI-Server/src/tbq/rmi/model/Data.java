package tbq.rmi.model;

import java.util.ArrayList;

public class Data {
	public static ArrayList<User> getData(){
		ArrayList<User> res = new ArrayList<>();
		res.add(new User("user", "user"));
		res.add(new User("system", "system"));
		return res;
	}

}
