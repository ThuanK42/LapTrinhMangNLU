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

public class Bai1 {

    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Nhap URL: ");
            String URL = input.nextLine();
            URL url = new URL(URL);
            System.out.println("URL is " + url.toString());

            System.out.println("Name of the file is: " + url.getFile());

            System.out.println("Host Name is: " + url.getHost());

            System.out.println("Port number is: " + url.getPort());

            System.out.println("Protocol type is: "
                    + url.getProtocol());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
