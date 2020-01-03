package TCP.ebanking;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import model.User;

public class DAOAccount {
	static List<User> listOfUser = new ArrayList<>();
	//Duong dan luu file user.txt
	public static String sourceFile = "banking/user.txt";
	static {
		try {
			loadDataFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadDataFromFile() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));
		String line = bf.readLine();// first line is title name
		line = bf.readLine();
		while (line != null) {
			StringTokenizer st = new StringTokenizer(line, "\t");
			String userName = st.nextToken();
			String passWord = st.nextToken();
			String accountNumber = st.nextToken();
			String balanceAccount = st.nextToken();
			listOfUser.add(new User(userName, passWord, accountNumber, Double.parseDouble(balanceAccount)));
			line = bf.readLine();
		}
		bf.close();
	}

	public static boolean checkUserName(String accountName) {
		for (User u : listOfUser) {
			if (u.checkUserName(accountName))
				return true;
		}
		return false;
	}

	public static boolean checkLogin(String accountName, String pass) {
		for (User u : listOfUser) {
			if (u.checkLogin(accountName, pass))
				return true;
		}
		return false;
	}

	public static void deposit(String money, String userName) {
		for(User u : listOfUser){
			if(u.getUserName().equals(userName)){
				u.setBalanceAccount(u.getBalanceAccount()+Double.parseDouble(money));
			}
		}
	}

	public static String getMoney(String num, String userName) {
		String reponse = "";
		for(User u : listOfUser){
			if(u.getUserName().equals(userName)){
				if(u.getBalanceAccount()< Double.parseDouble(num)){
					return "Account balance is not enough";
				}
				u.setBalanceAccount(u.getBalanceAccount()-Double.parseDouble(num));
				reponse="Please take money and balance your account is :"+u.getBalanceAccount();
			}
		}
		return reponse;
	}

	public static String tranfer(String userName, String accountNumber, String balance) {
		String reponse = "";
		reponse=getMoney(balance, userName);
		if(reponse.equals("Account balance is not enough")) return reponse;
		for(User u : listOfUser){
			if(u.getNumberAccount().equals(accountNumber)){
				u.setBalanceAccount(u.getBalanceAccount()+Double.parseDouble(balance));
				reponse="Transfered "+balance+" to account number: "+accountNumber;
			}
		}
		return reponse;
	}

	public static String infomationAccount(String userName) {
		for(User u : listOfUser){
			if(u.getUserName().equals(userName)){
				return "Number account is: "+u.getNumberAccount()+"\t balance is: "+u.getBalanceAccount();
			}
		}
		return "None infomation";
	}
}
