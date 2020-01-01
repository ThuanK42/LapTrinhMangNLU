package Tuan_8.Bai_20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MathServerThread extends Thread {
	private BufferedReader netIn;
	private PrintWriter netOut;
	private Socket socket;

	private double op1, op2, result;
	private char operator;

	public MathServerThread(Socket socket) throws IOException {
		this.socket = socket;
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		netOut = new PrintWriter(socket.getOutputStream(), true);
	}

	private void analyseRequest(String request) throws MathServerException {
		request = request.replaceAll("\\s+", "");
		String[] elements = request.split("[-+*/]");
		if (elements.length != 2)
			throw new MathServerException("Invalid command!");
		try {
			op1 = Double.parseDouble(elements[0]);
		} catch (NumberFormatException e) {
			throw new MathServerException("Operand 1 is not number.");
		}
		try {
			op2 = Double.parseDouble(elements[1]);
		} catch (NumberFormatException e) {
			throw new MathServerException("Operand 2 is not number.");
		}
		operator = request.charAt(elements[0].length());
	}

	private void processRequest() throws MathServerException {
		switch (operator) {
		case '+':
			result = op1 + op2;
			break;
		case '-':
			result = op1 - op2;
			break;
		case '*':
			result = op1 * op2;
			break;
		case '/':
			result = op1 / op2;
			if (Double.isInfinite(result))
				throw new MathServerException("!DIV/0");
			break;
		}
	}

	@Override
	public void run() {
		netOut.println("Welcome..");
		String request = "EXIT";
		try {
			while ((request = netIn.readLine()) != null) {
				try {
					if (request.equalsIgnoreCase("EXIT"))
						break;
					analyseRequest(request);
					processRequest();
					netOut.println(request + " = " + result);
				} catch (MathServerException e) {
					netOut.println(e.getMessage());
				}
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

@SuppressWarnings("serial")
class MathServerException extends Exception {
	public MathServerException(String message) {
		super(message);
	}
}
