package Tuan_7.Bai_18;


import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UploadServerThread extends Thread {
    private Socket socket;
    private DataInputStream netIn;

    public UploadServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.netIn = new DataInputStream(socket.getInputStream());
        System.out.println("Đã có client kết nối đến server.");
    }

    @Override
    public void run() {
        String fileName;
        long fileSize;
        BufferedOutputStream bos;
        try {
            while (true) {
                fileName = netIn.readUTF();
                fileSize = netIn.readLong();
                bos = new BufferedOutputStream(new FileOutputStream(fileName));
                for (long i = 0; i < fileSize; i++) {
                    bos.write(netIn.read());
                }
                bos.close();
            }
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}

