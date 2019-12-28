package Tuan_7.Bai_18;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server đang chạy...");
        while (true) {
            Socket socket = serverSocket.accept();
            UploadServerThread thread = new UploadServerThread(socket);
            thread.start();
        }
    }
}
