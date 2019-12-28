package Tuan_1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class BaiTapFile {
    /*
     * 1. Bài 1: (1)Hiện thực hàm boolean delete(String path) xóa tất cả những gì có
     * thể trong thư mục được chỉ định bởi path; trả về true nếu xóa thành công,
     * false nếu xóa không thành công
     */

    /*
     * Hiện thực hàm void copyAll(String sDir, String dDir String ext1, String ext2,
     * …) copy từ thư mục nguồn sDir vào thư mục đích dDir tất cả các file có phần
     * mở rộng quy định bởi ext1, ext2,…, extn;
     */

    private static void copyAll(String sDir, String dDir, String ext1, String ext2, String ext3) throws IOException {
        // srcfile la file thi copy
        File sf = new File(sDir);
        File df = new File(dDir);
        if (sf.isDirectory()) {
            // Thu muc dich chua ton tai thi tao moi
            if (!df.exists()) {
                df.mkdir();
                System.out.println("Thu muc da duoc tao " + df);
            }
            // Liet ke tat ca cac file va thu muc trong sourceFolder
            String files[] = sf.list();
            for (String file : files) {
                if (file.endsWith(ext1) || file.endsWith(ext2) || file.endsWith(ext3)) {
                    File srcFile = new File(sf, file);
                    File tarFile = new File(df, file);
                    // goi lai phuong thuc copyFolder
                    copyAll(srcFile.getAbsolutePath(), tarFile.getAbsolutePath(), ext1, ext2, ext3);
                }
            }
        } else {
            // copy file tu thuc muc nguon den thu muc dich
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
            byte[] buffer = new byte[1024 * 100];
            int length;
            while ((length = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            System.out.println("File da duoc copy " + df);
            bis.close();
            bos.close();
        }
    }

    /*
     * Hiện thực hàm void findAll(String path, String ext1, String ext2, String
     * ext2tìm và hiển thị đường dẫn đầy đủ file/folder chỉ định bởi path có phần mở
     * rộng quy định bởi ext1, ext2;
     */
    public static void findAll(String path, String ext, String ext2) {

        File sf = new File(path);
        if (!sf.exists()) {
            System.out.println("Khong ton tai duong dan");
        } else {
            if (sf.isFile()) {
                System.out.println("Duong dan la file");
            } else {
                if (sf.isDirectory()) {
                    File[] file2 = sf.listFiles();
                    File[] file3 = new File[file2.length];
                    for (int i = 0; i < file3.length; i++) {
                        if (file2[i].getAbsolutePath().endsWith(ext) || file2[i].getAbsolutePath().endsWith(ext2)) {
                            file3[i] = file2[i];
                            System.out.println("Duong dan file: " + file3[i].getAbsolutePath());
                        }
                        if (file2[i].isDirectory()) {
                            findAll(file2[i].getAbsolutePath(), ext, ext2);
                        }
                    }
                }
            }
        }
    }

    private static void deleteAll(String path, String ext, String ext2) {

        File sf = new File(path);
        if (!sf.exists()) {
            System.out.println("Khong ton tai duong dan");
        } else {
            if (sf.isFile()) {
                System.out.println("Duong dan la file");
            } else {
                if (sf.isDirectory()) {
                    File[] file2 = sf.listFiles();
                    File[] file3 = new File[file2.length];
                    for (int i = 0; i < file3.length; i++) {
                        if (file2[i].getAbsolutePath().endsWith(ext) || file2[i].getAbsolutePath().endsWith(ext2)) {
                            file3[i] = file2[i];
                            file3[i].delete();
                        }
                        if (file2[i].isDirectory()) {
                            deleteAll(file2[i].getAbsolutePath(), ext, ext2);
                        }
                    }
                }
            }
        }
    }

    private static boolean findFirst(String path, String pattern) {
        File inputPath = new File(path);
        if (!inputPath.exists()) {
            System.out.println("Khong ton tai duong dan");
            return false;
        } else {
            if (inputPath.isFile()) {
                System.out.println("path la file");
                return false;
            } else {
                if (inputPath.isDirectory()) {
                    File[] file = inputPath.listFiles();
                    for (File file2 : file) {
                        if (file2.isFile()) {
                            if (file2.getName().equalsIgnoreCase(pattern)) {
                                System.out.println(file2.getPath() + "\\" + pattern);
                                return true;
                            } else if (!file2.getName().equalsIgnoreCase(pattern)) {
                                System.out.println("Khong tim thay");
                                return false;
                            }
                        } else {
                            if (file2.isDirectory()) {
                                boolean re = findFirst(file2.getPath(), pattern);
                                if (re) {
                                    System.out.println(file2.getPath() + "\\" + pattern);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BaiTapFile btf = new BaiTapFile();

        String pathNoDir = "E:/ppp";
        String pathFile = "E:/abc/aaa.txt";
        String pathDir = "E:/abc";

//		System.out.println(btf.delete(pathNoDir));
//		System.out.println(btf.delete(pathFile));
//		System.out.println(btf.delete(pathDir));

        // System.out.println(b1.findFirst(pathDir, "aaaaa"));
        String s1 = "src\\Tuan_1\\TestExp\\";
        String s2 = "src\\Tuan_1\\TestExp2\\";
        String s3 = "src\\Tuan_1\\TestExpCopy\\";

        String ext = ".docx";
        String ext2 = ".txt";
        String ext3 = ".xlsx";
        // btf.copyAll(s1, s2, ext, ext2, ext3);
        // findAll(s1, ext, ext2);
        // deleteAll(s3, ext, ext2);

    }

}

