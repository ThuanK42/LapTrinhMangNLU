package model;

import java.util.Random;

public class User {
	String userName;
	String passWord;
	String numberAccount;
	double balanceAccount;

	public User(String userName, String passWord, String numberAccount, double balanceAccount) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.numberAccount = numberAccount;
		this.balanceAccount = balanceAccount;
	}

	public boolean checkUserName(String accountName ){
		return (accountName.equals(userName)) ? true:false;
	}
	
	public boolean checkLogin(String accountName,String pass){
		return(checkUserName(accountName)&& pass.equals(passWord))?true:false;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public double getBalanceAccount() {
		return balanceAccount;
	}

	public void setBalanceAccount(double balanceAccount) {
		this.balanceAccount = balanceAccount;
	}
	
}
