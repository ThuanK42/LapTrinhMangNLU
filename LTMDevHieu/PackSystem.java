import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PackSystem {
    public static boolean pack(String sFolder, String dFile) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(dFile, "rw");
        BufferedInputStream bis;
        File path = new File(sFolder);

        // Neu duong dan la thu muc thi tien hanh doc cac file con
        if (path.isDirectory()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length - 1; i++) {
                // tien hanh doc/ghi neu cac file con khong phai la thu muc
                if (!files[i].isDirectory()) {
                    bis = new BufferedInputStream(new FileInputStream(files[i]));
                    // lay vi tri con tro bat dau ghi file
                    long pos1 = raf.getFilePointer();
                    // ghi 8 byte de danh cho viec ghi vi tri file
                    raf.writeLong(0);
                    // ghi file length
                    raf.writeLong(files[i].length());
                    // ghi file name
                    raf.writeUTF(files[i].getName());
                    // ghi data
                    int value;
                    while ((value = bis.read()) != -1) {
                        raf.write(value);
                    }
                    // lay vi tri con tro bat dau ghi file tiep theo
                    long pos2 = raf.getFilePointer();
                    // chuyen ve vi tri ma bat dau ta ghi byte ao luc nay de ghi vi tri bat dau file
                    // tiep theo
                    raf.seek(pos1);
                    raf.writeLong(pos2);
                    // chuyen lai pos2 de bat dau vong lap moi cho file tiep theo
                    raf.seek(pos2);
                    bis.close();
                }
            }

            // thuc hien thao tac voi file cuoi cung
            File finalFile = files[files.length - 1];
            if (!finalFile.isDirectory()) {
                bis = new BufferedInputStream(new FileInputStream(finalFile));
                // ghi vi tri 0. Tuc la vi tri ghi file dau tien
                raf.writeLong(0);
                // ghi file length
                raf.writeLong(finalFile.length());
                // ghi file name
                raf.writeUTF(finalFile.getName());
                // ghi data
                int value;
                while ((value = bis.read()) != -1) {
                    raf.write(value);
                }
            }
            raf.close();
            return true;
        }
        raf.close();
        return false;
    }

    public static void main(String[] args) throws IOException {
        String sFolder = "E:\\Ronaldo\\Messi";
        String dFile = "E:\\Ronaldo\\Messi\\Kaka\\out.pack";
        boolean result = pack(sFolder, dFile);
        System.out.println(result);
    }
}
