package Tuan_9;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    final static int port = 2000;

    private void save(BufferedOutputStream out, DataInputStream read) {
    }

    public static void main(String[] args) throws IOException {
        byte[] data = new byte[1024 * 10];
        //connect
        System.out.println("waiting for connection from clients .......");
        DatagramSocket ds = new DatagramSocket(port);
        //process
        DatagramPacket packetClient = new DatagramPacket(data, data.length);
        ds.receive(packetClient);
        InetAddress add = packetClient.getAddress();
        int pClient = packetClient.getPort();
        DataInputStream read = new DataInputStream(new ByteArrayInputStream(packetClient.getData()));
        String commandClient = read.readUTF();
        System.out.println("Client send ..." + commandClient);
        // phan tich
        String[] anysis = commandClient.split(" ");
        String comCopy = anysis[0];// dong lenh
        String srcFile = anysis[1];// nguon file
        String destFile = anysis[2]; // dich file
        //
        if (comCopy.equalsIgnoreCase("Copy")) {
            String respond = "Upload " + srcFile + " " + destFile;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            DataOutputStream send = new DataOutputStream(bout);
            send.writeUTF(respond);
            byte[] arrSend = bout.toByteArray();
            DatagramPacket sendRe = new DatagramPacket(arrSend, arrSend.length);
            ds.send(sendRe);
            // uploaded -> close
            UDPServer sd = new UDPServer();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
            read = new DataInputStream(new FileInputStream(srcFile));
            sd.save(out, read);

        }
    }

}
