package Tuan_8.Bai_21;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    final static int port = 4444;
    final static String ip = "127.0.0.1";

    public static void sendStudentTCP() throws IOException {
        Socket sc = new Socket(ip, port);
        System.out.println("Connect to server");
        // User input
        System.out.println("findByName name.......");
        System.out.println("findByAge  age.......");
        System.out.println("findByScore score.......");
        BufferedReader fs = new BufferedReader(new InputStreamReader(System.in));

        // read request from user
        String readUser = fs.readLine();

        // send request to server
        PrintWriter ws = new PrintWriter(sc.getOutputStream(), true);
        ws.println(readUser);

        // get data from server
        BufferedReader getServer = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        String line = getServer.readLine();
        System.out.println("recive   :" + line);

        sc.close();
        fs.close();
        ws.close();
        getServer.close();
    }

    public static void main(String[] args) throws IOException {
        sendStudentTCP();
    }
}
