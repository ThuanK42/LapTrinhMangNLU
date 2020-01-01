import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IO {
    private static String ext(long index) {
        /*
        Lam phan ten tep 00+ gi do
         */
        String res = "" + index;
        while (res.length() < 3)
            res = "0" + res;
        return res;
    }

    public static void split(String sFile, long pSize) throws IOException {
        File file = new File(sFile); // tao file
        long fSize = file.length(); // dung luong file
        long count = fSize / pSize; // file duoc chia lam may phan = dung luong file / dung luong 1 tep khi nen (psize)
        boolean hasRest = (fSize % pSize) > 0; // phan du khi chia = duoi file

        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos;
        int data;

        String dFile;

        for (int index = 1; index <= count; index++) {
            dFile = sFile + "." + ext(index); // ten file sau khi chia = ten file ban dau +.+ 00 gi do
            fos = new FileOutputStream(dFile);

            for (long i = 0; i < pSize; i++) {
                data = fis.read(); // doc ghi tung byte du lieu
                fos.write(data);
            }

            fos.close();
        }
        if (hasRest) {
            dFile = sFile + "." + ext(count + 1);
            fos = new FileOutputStream(dFile);
            while ((data = fis.read()) != -1)
                fos.write(data);
            fos.close();

        }
        fis.close();
    }

    public static void main(String[] args) throws IOException {
        String slide = "C:\\Users\Admin\Downloads\BG_CNPM.ppt";
        int psize = 10;
        split(slide, psize);

    }
}
