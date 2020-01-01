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
import java.util.Scanner;

public class Bai3 {

    public static void main(String args[]) {

        while (true) {
            int i = 0;
            Scanner input = new Scanner(System.in);
            System.out.print("Nhap URL: ");
            String URL = input.nextLine();
//            String URL = "http://tinchi.dut.edu.vn";
            try {
                URL ul = new URL(URL);
                URLConnection u = ul.openConnection();
                String s = u.getHeaderField(i);
                String sg = u.getHeaderFieldKey(i);
                while (s != null) {
                    System.out.println("Header " + i + ": " + sg + " = " + s);
                    i++;
                    s = u.getHeaderField(i);
                    sg = u.getHeaderFieldKey(i);
                }
            } catch (MalformedURLException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
