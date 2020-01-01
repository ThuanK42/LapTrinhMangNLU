package Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Multicast_Received {
    public static void main(String[] args) {
        try {
            byte[] receivedData = new byte[256];
            InetAddress ip = InetAddress.getByName("225.225.225.225");
            int port = 2222;
            String temp;
            
            MulticastSocket receivedSocket = new MulticastSocket(port);
            receivedSocket.joinGroup(ip);
            
            while(true){
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                receivedSocket.receive(receivedPacket);
                temp = new String(receivedPacket.getData()).trim();
                System.out.println(temp);
            }
            
            
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 