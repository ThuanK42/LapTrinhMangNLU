package TCP.ebanking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class BankServices extends Thread {
	Socket s;
	PrintWriter netOut;
	BufferedReader netIn;
	String keyWord;
	boolean checkLogin = false;
	String message = "";
	String userName;
	//ky tu ngan cach giua cac tham so
	String delimDefault = " ";

	public BankServices(Socket socket) throws IOException {
		this.s = socket;
		netIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		netOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
	}

	@Override
	public void run() {
		netOut.println("Welcome to NLU e-Banking....");
		try {
			while (!checkLogin) {
				String request = netIn.readLine();
				if ("QUIT".equalsIgnoreCase(request)) {
					message = "Thanks.Goodbye and see you again...";
					netOut.println(message);
					break;
				}
				message = checkLogin(request);
				netOut.println(message);
				message="";
			}
			while (checkLogin) {
				String request = netIn.readLine();
				if ("QUIT".equalsIgnoreCase(request)) {
					message = "Thanks.Goodbye and see you again...";
					netOut.println(message);
					break;
				}
				message = analysisRequest(request);
				netOut.println(message);
				message="";
			}

		} catch (IOException e) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

	}

	private String analysisRequest(String request) {
		StringTokenizer st = new StringTokenizer(request, delimDefault);
		keyWord = st.nextToken().toUpperCase();
		switch (keyWord) {
		// Nap tien vao tai khoan : GUI SOTIEN <==> DEPOSIT SOTIEN
		case "DEPOSIT":
			String money = st.nextToken();
			DAOAccount.deposit(money, userName);
			message = "Deposit success! ";
			break;
		case "GET" :
			String num = st.nextToken();
			message=DAOAccount.getMoney(num,userName);
			break;
		// chuyen tien dung tu khoa TRANFER SOTAIKHOAN SOTIEN
		case "TRANFER":
			String accountNumber = st.nextToken();
			String balance=st.nextToken();
			message=DAOAccount.tranfer(userName,accountNumber,balance);
			break;
		case "INFO":
			message=DAOAccount.infomationAccount(userName);
			break;
		default:
			message="Key word command not exactly !!!";
			break;
		}
		return message;
	}

	public String checkLogin(String request) {
		StringTokenizer st = new StringTokenizer(request, delimDefault);
		keyWord = st.nextToken().toUpperCase();
		switch (keyWord) {
		case "USER":
			userName = st.nextToken();
			if (DAOAccount.checkUserName(userName)) {
				message = "AccountName is exists";
			} else {
				message = "AccountName is not exists!!!";
			}
			break;
		case "PASS":
			String pass = st.nextToken();
			if (userName == null) {
				message = "You must enter an account name... ";
				break;
			}
			if (DAOAccount.checkLogin(userName, pass)) {
				checkLogin = true;
				message = "Login success!";
			} else {
				message = "Login fail!!!";
			}
			break;
		default:
			message = "Key word command not exactly.Please re-checked!!!!";
			break;
		}
		return message;
	}
}
