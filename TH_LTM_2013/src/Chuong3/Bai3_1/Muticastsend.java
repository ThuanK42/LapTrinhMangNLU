package Chuong3.Bai3_1;
import java.io.*;
import java.net.*;
public class Muticastsend {
  public static void main(String[] args) {
    MulticastSocket socket = null;
    DatagramPacket outPacket = null;
    DatagramPacket inPacket = null;
    byte[] inBuf = new byte[256];
    byte[] outBuf;
    final int PORT = 50001;
    try {
      socket = new MulticastSocket(PORT);
      long counter = 0;
      String msg;
      InetAddress address = InetAddress.getByName("238.0.0.1");
      socket.joinGroup(address);
      while (true) {
        msg = "This is multicast! " + counter;
        counter++;
        outBuf = msg.getBytes();
 
        //Send to multicast IP address and port
        outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);
 
        socket.send(outPacket);
 
        System.out.println("sends : " + msg);
        
        inPacket = new DatagramPacket(inBuf, inBuf.length);
        socket.receive(inPacket);
        msg = new String(inBuf, 0, inPacket.getLength());
        System.out.println("Received : " + msg);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ie) {
        }
      }
    } catch (IOException ioe) {
      System.out.println(ioe);
    }
  }
}

