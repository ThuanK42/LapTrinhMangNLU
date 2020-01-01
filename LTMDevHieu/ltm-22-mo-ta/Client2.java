
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client2 {
	static String host = "127.0.0.1";
	static int port = 555;

	public static void findStudent() throws IOException, ClassNotFoundException {
		while (true) {
			// establish client socket
			Socket socket = new Socket(host, port);// mỗi khi new socket nó sẽ tự động kết nối đến server luôn
			// server đang block ở server.accept(), khi new sẽ bỏ block và tiếp tục thực
			// hiện server
			//mỗi bước lặp của vòng lặp while sẽ tạo một kết nối mới đến server
			System.out.println("Please input");
			System.out.println("<command>_<query>");
			// b1: nhan input user tu ban phim
			BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
			String user = inputUser.readLine();

			// b2: gui input user len server

			PrintWriter sendServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			sendServer.println(user);
			System.out.println("Input recieved: " + user);

			// b4: nhan KQ gui ve tu server
			BufferedReader receiveFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String result = receiveFromServer.readLine();

			// b5: dong nguon
			if (result == "") {
				System.out.println("Query not found");
			} else if (result.equalsIgnoreCase("Exit")) {
				System.out.println("Client GoodBye!");
				socket.close();
				return;
			} else {
				System.out.println("Result from server: " + result + "\n********");
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		findStudent();
	}

}
