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
import java.util.*;

public class Bai5 {

    public static void main(String args[]) throws IOException {
        int i;
        Scanner input = new Scanner(System.in);
        System.out.print("Nhap URL: ");
        String URL = input.nextLine();
        try {
            URL ul = new URL(URL);
            URLConnection u = ul.openConnection();
            System.out.println("Date: " + new Date(u.getDate()));
            System.out.println("Content-type:" + u.getContentType());
            System.out.println("Expires: " + u.getExpiration());
            System.out.println("Last Modified: " + u.getLastModified());
            int l = u.getContentLength();
            System.out.println("Content_length: " + l);
            if (l > 0) {
                System.out.println("Content");
                InputStream is = u.getInputStream();
                int a = l;
                while (((i = is.read()) != -1) && (--a > 0)) {
                    System.out.print((char) i);
                }
                is.close();
            } else {
                System.out.println("Content is not available");
            }
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

    }
}
