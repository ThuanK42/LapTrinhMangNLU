package Tuan_1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhanIO {
    /*
     * Bài 5: Viết CT copy/move file dùng byte array kết hợp với BIS, BOS: boolean
     * fileCopy(String sFile, String destFile,boolean moved)
     */
    /*
     * Em không biết biết moved là gì nên em chọn nó nếu nó moved = true thì cho nó
     * là phương thức moveFile còn nếu moved=fase thì em cho là phương thức copyfile
     */
    /*
     * MoveFile thi xoa file cu, copy no qua cho khac em nghi vay
     */
    private static boolean fileCopy(String sFile, String destFile, boolean moved) throws IOException {
        File sf = new File(sFile);
        File df = new File(destFile);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
        int length;
        byte[] data = new byte[1024 * 100];
        if (!sf.exists()) {
            System.out.println("Path not exist");
            return false;
        } else {
            if (moved == true) {// move
                while ((length = bis.read(data)) > -1) {
                    bos.write(data, 0, length);
                }
                sf.delete();
                System.out.println("File is moved successful!");
                bis.close();
                bos.close();
                return true;
            } else {// copy
                while ((length = bis.read(data)) > -1) {
                    bos.write(data, 0, length);
                }
                System.out.println("File is copy successful!");
                bis.close();
                bos.close();
                return true;
            }
        }
    }

    /*
     * Bài 6: Viết CT copy/move thư mục dùng byte array kết hợp với BIS, BOS:
     * boolean folderCopy(String sFolder, String destFolder, boolean moved);
     */
    // moved =true va nguoc lai

    private static boolean folderCopy(String sFile, String destFile, boolean moved) throws IOException {
        File sf = new File(sFile);
        File df = new File(destFile);
        if (!sf.exists()) {
            System.out.println("Duong dan khong ton tai");
            return false;
        } else { // copy
            if (!moved) {
                if (sf.isDirectory()) {
                    // Thu muc dich chua ton tai thi tao
                    if (!df.exists()) {
                        df.mkdir();
                        System.out.println("Thu muc dich duoc tao: " + df);
                    }
                    String files[] = sf.list();
                    for (String file : files) {
                        File srcFile = new File(sf, file);
                        File desFile = new File(df, file);
                        folderCopy(srcFile.getAbsolutePath(), desFile.getAbsolutePath(), moved);
                    }
                } else {
                    fileCopy(sf.getAbsolutePath(), df.getAbsolutePath(), false);
                }
                return true;
            } else { // move
                if (sf.isDirectory()) {
                    // Thu muc dich chua ton tai thi tao
                    if (!df.exists()) {
                        df.mkdir();
                        System.out.println("Thu muc dich duoc tao: " + df);
                    }
                    String files[] = sf.list();
                    for (String file : files) {
                        File srcFile = new File(sf, file);
                        File desFile = new File(df, file);
                        folderCopy(srcFile.getAbsolutePath(), desFile.getAbsolutePath(), moved);
                    }
                } else {
                    fileCopy(sf.getAbsolutePath(), df.getAbsolutePath(), false);
                }
                sf.delete();
                return true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PhanIO io = new PhanIO();
        String sFile = "src\\Tuan_1\\File\\fake.jpg";
        String destFile = "src\\Tuan_1\\File\\fake3.jpg";
        String sf = "src\\Tuan_1\\File\\fake2.jpg";
        String df = "src\\Tuan_1\\Move\\fake3.jpg";
        String s1 = "src\\Tuan_1\\ThuMucNguon";
        String s2 = "src\\Tuan_1\\ThuMucDich";
        //System.out.println(io.fileCopy(sFile, destFile, false));//copy
//		System.out.println(io.fileCopy(sf, df, true));//move
        System.out.println(folderCopy(s1, s2, false));//copy
//		System.out.println(folderCopy(s1, s2, true));//move

    }
}

