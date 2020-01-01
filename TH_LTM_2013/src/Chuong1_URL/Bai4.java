/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chuong1_URL;

/**
 *
 * @author HOAI-THU
 */
import java.io.*;
import java.net.*;

public class Bai4 {

    public static void main(String args[]) {

        InetAddress i;
        try {
            i = InetAddress.getLocalHost();
            System.out.println("The localhost is: " + i);
            i = InetAddress.getByName(null);
            System.out.println("The Null host is: " + i);
            i = InetAddress.getByName("tinchi.dut.edu.vn");
            System.out.println("The Host address is: " + i);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
