package Tuan_7.Bai_18;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class UploadClient {
    private Socket socket;
    private DataOutputStream netOut;
    private String csn;

    public UploadClient() {
        this("UTF-8");
    }

    public UploadClient(String csn) {
        this.csn = csn;
    }

    public void connect() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in, csn));
        String temp;
        System.out.print("Nhập host [localhost]: ");
        temp = input.readLine();
        String host = temp.equals("") ? "localhost" : temp;
        int port = -1;
        do {
            System.out.print("Nhập port trong đoạn 0-65535 [12345]: ");
            temp = input.readLine();
            try {
                port = temp.equals("") ? 12345 : Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                System.out.println("Port là số!");
            }
        } while (port < 0 || port > 65535);
        connect(host, port);
    }

    public void connect(String host, int port) throws IOException {
        System.out.println("Đang kết nối đến " + host + ":" + port + "...");
        socket = new Socket(host, port);
        System.out.println("Kết nối thành công.");
        netOut = new DataOutputStream(socket.getOutputStream());
    }

    public boolean upload(String sFilename, String dFilename) {
        boolean result = false;
        try {
            File sFile = new File(sFilename);
            netOut.writeUTF(dFilename);
            netOut.writeLong(sFile.length());
            netOut.flush();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sFile));
            int data;
            while ((data = bis.read()) != -1) {
                netOut.write(data);
            }
            netOut.flush();
            bis.close();
            result = true;
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    public void receiveRequest() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in, csn));
        String line;
        do {
            System.out.print("Nhập lệnh: ");
            line = input.readLine();
            if (line.equalsIgnoreCase("EXIT")) {
                System.out.println("Thoát chương trình.");
                break;
            }
            String[] elements = line.split("\\s+");
            if (!elements[0].equalsIgnoreCase("UPLOAD") || elements.length != 3) {
                System.out.println("Lệnh không hợp lệ!");
            } else {
                if (upload(elements[1], elements[2]))
                    System.out.println("Upload thành công.");
                else
                    System.out.println("Upload không thành công!");
            }
        } while (line != null);
        socket.close();

    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        UploadClient client1 = new UploadClient();
        client1.connect("localhost", 12345);
        client1.receiveRequest();
    }

}
