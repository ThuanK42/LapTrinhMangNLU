package serverSide.dao;

import java.util.ArrayList;
import java.util.List;

import serverSide.model.Account;

public class AccountDAO {
	public static List<Account> accounts = new ArrayList<>();

	static {
		accounts.add(new Account("pvtinh", "12345"));
		accounts.add(new Account("pvthuan", "56789"));
	}

	public static boolean isUserExist(String username) {
		for (Account account : accounts) {
			if (account.getUsername().equalsIgnoreCase(username))
				return true;
		}
		return false;
	}

	public static boolean login(String lastUsername, String password) {
		for (Account account : accounts) {
			if (account.login(lastUsername, password))
				return true;
		}
		return false;
	}
}
