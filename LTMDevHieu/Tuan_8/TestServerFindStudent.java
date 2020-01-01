package Tuan_8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServerFindStudent {
    // input cua client la output cua server
    // client dung input de doc du lieu  -> output cua client doc input cua client
    // input cua serevre doc output cua client
    // out truyen du ieu, input nhan du lieu
    static int port = 12345;

    public static void main(String[] args) throws IOException {

        //Connect
        ServerSocket serverSocket = new ServerSocket(port); // mo cong
        System.out.println("waiting for connection client");
        Socket socket = serverSocket.accept(); // chap nhan cong
        System.out.println("Accept connection from Client");
        //process
        DataInputStream readClient = new DataInputStream(socket.getInputStream());
        String comment = readClient.readUTF(); // lay cau lenh nguoi dung
        String[] anysis = comment.split("\t"); // cat chuoi cau lenh theo tab

        String com = anysis[0]; // lenh
        String valueFind = anysis[1]; // gia tri can tim kiem theo

        DataOutputStream sendClient = new DataOutputStream(socket.getOutputStream());
        if (com.equalsIgnoreCase("findByName")) {
            FileServer fileServer = new FileServer();
            String result = fileServer.findByName(valueFind); // tra ve danh sach ten
            if (result == "") {
                sendClient.writeUTF("Khong tim thay ten");
            } else {
                sendClient.writeUTF(result);
            }
        } else if (com.equalsIgnoreCase("findByLessAge")) {
            FileServer fileServer = new FileServer();
            String result = fileServer.findByLessAge(Integer.parseInt(valueFind));// tra ve thong tin sinh vien
            if (result == "") {
                sendClient.writeUTF("Khong tim thay tuoi");
            } else {
                sendClient.writeUTF(result);
            }

        } else if (com.equalsIgnoreCase("findByLessScore")) {
            FileServer fileServer = new FileServer();
            String result = fileServer.findByLessScore(Double.parseDouble(valueFind));// tra ve thong tin sinh vien
            if (result == "") {
                sendClient.writeUTF("Khong tim thay diem");
            } else {
                sendClient.writeUTF(result);
            }

        } else {
            sendClient.writeUTF("Sai cu phap");
        }
        serverSocket.close();

    }
}
