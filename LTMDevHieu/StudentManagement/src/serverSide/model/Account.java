package serverSide.model;

public class Account {
	private String username;
	private String password;

	public Account() {
	}

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public boolean login(String lastUsername, String password) {
		return this.username.equalsIgnoreCase(lastUsername) && this.password.equals(password);
	}

}
