package Tuan_9;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    static int port = 2000;
    static String localhost = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        byte[] send = new byte[1024 * 10];
        //Connect
        DatagramSocket ds = new DatagramSocket();
        //
        System.out.println("User input correct format: Copy srcFile destFile");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String commuser = userInput.readLine();
        //
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream in = new DataOutputStream(bout);
        in.writeUTF(commuser);
        byte[] arr = bout.toByteArray();
        InetAddress addClient = InetAddress.getByName(localhost);
        DatagramPacket sendServer = new DatagramPacket(send, send.length);
        sendServer.setData(arr);
        sendServer.setAddress(addClient);
        sendServer.setPort(port);
        ds.send(sendServer);
        //
        DatagramPacket receiveServer = new DatagramPacket(send, send.length);
        ds.receive(receiveServer);

        DataInputStream readServer = new DataInputStream(new ByteArrayInputStream(receiveServer.getData()));
        String sendServerRe = readServer.readUTF();
        System.out.println("Server send: " + sendServerRe);

        //
        String[] anysis = sendServerRe.split(" ");
        String comUpload = anysis[0];
        String srcFile = anysis[1];
        //
        if (comUpload.equalsIgnoreCase("Upload")) {
            //ClientUpload_Download cd = new ClientUpload_Download();
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File(srcFile)));
          //  cd.upload(bin, out);
        }
        ds.close();

    }
}
