package Tuan_8;

import java.io.*;
import java.net.Socket;

public class FileClient {
    static int port = 12345;
    static String localHost = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        // Connect
        System.out.println("Send require connect Server .........");
        Socket socket = new Socket(localHost, port);
        System.out.println("Connected to Server ......");
        // process problem
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Correct format: findByName \tab name");

        String userComm = userInput.readLine();
        DataOutputStream sendServer = new DataOutputStream(socket.getOutputStream());
        sendServer.writeUTF(userComm);
        //
        DataInputStream readServer = new DataInputStream(socket.getInputStream());
        String result = readServer.readUTF();
        System.out.println(result);
        socket.close();

    }
}
