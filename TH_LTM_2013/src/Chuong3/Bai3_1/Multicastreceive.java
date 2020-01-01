package Chuong3.Bai3_1;

import java.io.*;
import java.net.*;

public class Multicastreceive {
  public static void main(String[] args) {
    MulticastSocket socket = null;
    DatagramPacket inPacket = null;
    byte[] inBuf = new byte[256];
    try {
      //Prepare to join multicast group
      socket = new MulticastSocket(50001);
      InetAddress address = InetAddress.getByName("238.0.0.1");
      socket.joinGroup(address);
      inPacket = new DatagramPacket(inBuf, inBuf.length);
      socket.receive(inPacket);
      String msg = new String(inBuf, 0, inPacket.getLength());
      System.out.println(" Msg : " + msg);
      socket.leaveGroup(address);
    } catch (IOException ioe) {
      System.out.println(ioe);
    }
  }
}