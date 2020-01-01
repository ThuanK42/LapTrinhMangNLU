/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chuong1_URL;

/**
 *
 * @author HOAI-THU
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Scanner;

public class Bai2 {

    public static void main(String arg[]) {
        int i;
        InputStream bis;
        //Nhap URL
        Scanner _sc = new Scanner(System.in);
//        System.out.print("Nhap URL :");
//        String url = _sc.nextLine();
        try {
            URL u = new URL("http://tinchi.dut.edu.vn");
            bis = (InputStream) u.getContent();
            while ((i = bis.read()) > 0) {
                System.out.print((char) i);
            }
            System.out.println();
        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
