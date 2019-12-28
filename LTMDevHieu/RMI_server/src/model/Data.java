package model;

import java.util.ArrayList;

public class Data {
	public ArrayList<User> list() {
		ArrayList<User> list = new ArrayList<>();
		list.add(new User("admin", "admin"));
		list.add(new User("user1", "12345"));
		return list;
	}
}
