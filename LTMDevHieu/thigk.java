import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class thigk {
    public boolean exits(String file) {
        return new File(file).exists();
    }

    public boolean creatFolder(String path) {
        return new File(path).mkdirs();
    }

    public boolean creatFile(String path) throws IOException {
        return new File(path).createNewFile();
    }

    public boolean deleteFile(String file) {
        File f = new File(file);
        return (f.isFile()) ? f.delete() : false;
    }

    public boolean deleteFolder(String folder) {
        File f = new File(folder);
        if (!f.exists()) return false;
        if (f.isDirectory()) {
            File listFile[] = f.listFiles();
            for (File file : listFile) {
                if (file.isFile()) file.delete();
                if (file.isDirectory()) deleteFolder(file.getAbsolutePath());
            }
        }
        return f.delete();
    }

    List<File> listName = new ArrayList<>();
    List<File> listExt = new ArrayList<>();

    //search file by name
    public List<File> findName(String sourceFile, String name) {
        File sf = new File(sourceFile);
        if (sf.isFile() && sf.getName().substring(0, sf.getName().lastIndexOf(".")).equals(name)) listName.add(sf);
        if (!sf.exists()) return null;
        if (sf.isDirectory()) {
            File listFile[] = sf.listFiles();
            for (File f : listFile) {
                if (f.isFile() && f.getName().substring(0, f.getName().lastIndexOf(".")).equals(name)) listName.add(f);
                if (f.isDirectory()) findName(f.getAbsolutePath(), name);
            }
        }
        return listName;

    }

    //search file by extensiton
    public List<File> findExt(String sourceFile, String extension) {
        File sf = new File(sourceFile);
        if (sf.isFile() && sf.getName().endsWith(extension)) listExt.add(sf);
        if (!sf.exists()) return null;
        if (sf.isDirectory()) {
            File listFile[] = sf.listFiles();
            for (File f : listFile) {
                if (f.isFile() && f.getName().endsWith(extension)) listExt.add(f);
                if (f.isDirectory()) findExt(f.getAbsolutePath(), extension);
            }
        }
        return listExt;

    }

    String tmp = "";
    String res = "";

    //show directory tree of path folder
    public String dirTree(String source) {
        File sf = new File(source);
        if (!sf.exists()) return "File not exists";
        //Neu no la file thi tra ve cai nay va dung chuong trinh
        if (sf.isFile()) res += "-" + sf.getName();
        //Neu la thu muc
        if (sf.isDirectory()) {
            File listFile[] = sf.listFiles();
            //Lay ra ten thu muc goc
            res += tmp + "+ " + sf.getName() + "\n";
            for (File f : listFile) {
                if (f.isFile()) {
                    //lay ra ten file,dong thoi thut vao dau dong
                    tmp += " ";
                    res += tmp + "- " + f.getName() + "\n";
                    //Sau khi thut dau dong thi phai tra lai vi tri cu
                    tmp = tmp.substring(0, tmp.length() - 1);
                }
                if (f.isDirectory()) {
                    //phai thut vao dau dong vi dang goi de quy
                    tmp += " ";
                    dirTree(f.getAbsolutePath());
                    //Tra lai vi tri cu sau khi goi de quy
                    tmp = tmp.substring(0, tmp.length() - 1);
                }
            }
        }
        return res;
    }

    public void copyFile(String soucreFile, String destinationFile) throws IOException {
        File sf = new File(soucreFile);
        File df = new File(destinationFile);

        if (!sf.exists()) return;
        if (sf.isDirectory()) return;
        if (sf.isFile()) {
            InputStream is = new FileInputStream(sf);
            OutputStream os = new FileOutputStream(df);

            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(os);

            byte[] arr = new byte[1024];
            while (is.read(arr) != -1) os.write(arr);

            bis.close();
            bos.close();
            System.out.println("copy file success");
        }
    }

    //copy folder
    public void copy(String soucreFile, String destinationFile) throws IOException {
        File sf = new File(soucreFile);
        File df = new File(destinationFile);
        df.mkdirs();
        if (!sf.exists()) return;
        if (sf.isDirectory()) {
            File listFile[] = sf.listFiles();
            for (File f : listFile) {
                if (f.isFile()) copyFile(f.getAbsolutePath(), destinationFile + File.separator + f.getName());
                if (f.isDirectory()) copy(f.getAbsolutePath(), destinationFile + File.separator + f.getName());
            }
            System.out.println("copy folder success");
        }
        if (sf.isFile()) copyFile(soucreFile, destinationFile);
    }

    public void splitFile(String sourceFile, String destinationFile, long size) throws IOException {
        File sf = new File(sourceFile);
        File desFile = new File(destinationFile);
        desFile.mkdirs();
        if (!sf.exists()) return;
        if (sf.isDirectory()) return;

        long fileSize = sf.length();
        long count = fileSize / size;
        long remain = fileSize % size;

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));

        if (sf.isFile()) {
            for (int index = 1; index <= count; index++) {
                String df = destinationFile + File.separator + sf.getName() + "." + index;
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
                for (int i = 1; i <= size; i++)
                    bos.write(bis.read());
                    bos.close();
            }
            if (remain > 0) {
                String df = destinationFile + File.separator + sf.getName() + "." + (count + 1);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
                int data = 0;
                while ((data = bis.read()) != -1) bos.write(data);
                bos.close();
            }
            System.out.println("Split file success");
        }
        bis.close();
    }

    public void joinFile(String sourceFile, String destinationFile) throws IOException {
        File sf = new File(sourceFile);
        File df = new File(destinationFile);
        if (!sf.exists()) return;
        if (sf.isFile()) return;
        if (sf.isDirectory()) {
            File listFile[] = sf.listFiles();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(sf + File.separator + df));
            for (File f : listFile) {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f.getAbsolutePath()));
                byte arr[] = new byte[1024 * 100];
                while (bis.read(arr) != -1) bos.write(arr);
                bis.close();
                f.delete();
            }
            bos.close();
            System.out.println("join file success");
        }
    }

    public void saveListOfStudentToFile(List<Student> list, String destinationFile) throws IOException {
        File df = new File(destinationFile);

        DataOutputStream dos = new DataOutputStream(new FileOutputStream(df));
        dos.writeInt(list.size());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).save(dos);
        }
        System.out.println("Save list of student success");
        dos.close();
    }

    public List<Student> loadFileToListOfStudent(String sourceFile) throws IOException {
        File sf = new File(sourceFile);
        List<Student> list = new ArrayList<>();
        DataInputStream dis = new DataInputStream(new FileInputStream(sf));
        int count = dis.readInt();
        for (int i = 0; i < count; i++) {
            list.add(new Student(dis.readInt(), dis.readUTF(), dis.readDouble()));
        }
        dis.close();
        return list;
    }

    public void exportListOfStudentToFileText(List<Student> list, String destinationFile, String delimited, String charset) throws IOException {
        File df = new File(destinationFile);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(df), charset));
        for (Student st : list) {
            pw.println(st.lineDataStudent(delimited));
        }
        pw.close();
    }

    public List<Student> importFileTextToListOfStudent(String sourceFile, String delimited, String charset) throws IOException {
        List<Student> list = new ArrayList<>();
        File sf = new File(sourceFile);
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(sf), charset));
        String lineData = "";
        while ((lineData = bf.readLine()) != null) {
            String arrData[] = lineData.split(delimited);
            Student st = new Student(Integer.parseInt(arrData[0]), arrData[1]);
            for (int i = 2; i < arrData.length; ) {
                Subject subject = new Subject(arrData[i], arrData[i + 1], arrData[i + 2], Double.parseDouble(arrData[i + 3]));
                st.addSubject(subject);
                i = i + 4;
            }
            list.add(st);
        }
        bf.close();
        return list;
    }

    public void packFile(String folder, String archive, boolean delete) throws IOException {
        File sf = new File(folder);
        if (!sf.exists()) return;
        RandomAccessFile raf = new RandomAccessFile(archive, "rw");
        File listFile[] = sf.listFiles();
        raf.writeInt(listFile.length);
        for (File f : listFile) {
            raf.writeUTF(f.getName());
            raf.writeLong(f.length());
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            int data = 0;
            while ((data = bis.read()) != -1) raf.write(data);

            bis.close();
        }
        raf.close();
        System.out.println("zip file success");
    }

    public void unpack(String archive, String destinationFile) throws IOException {
        File sf = new File(archive);
        if (!sf.exists()) return;

        RandomAccessFile raf = new RandomAccessFile(new File(archive), "rw");
        int count = raf.readInt();
        for (int i = 0; i < count; i++) {
            String fname = raf.readUTF();
            long size = raf.readLong();
            System.out.println(fname);
            File df = new File(destinationFile + File.separator + fname);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
            for (int s = 0; s < size; s++) bos.write(raf.read());
            bos.close();
        }
        raf.close();
        System.out.println("restore list file from file zip success");
    }

    public File unpackFile(String archive, String destinationFile, String fileName) throws IOException {
        File sf = new File(archive);
        if (!sf.exists()) return null;

        RandomAccessFile raf = new RandomAccessFile(new File(archive), "rw");
        int count = raf.readInt();
        for (int i = 0; i < count; i++) {
            String fname = raf.readUTF();
            long size = raf.readLong();
            long pos = raf.getFilePointer();
            if (fname.equals(fileName)) {
                File df = new File(destinationFile + File.separator + fname);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
                for (int s = 0; s < size; s++) bos.write(raf.read());
                bos.close();
                raf.close();
                System.out.println("Unpack file success");
                return df;
            } else {
                raf.seek(pos + size);
            }
        }
        System.out.println("Unpack file fail");
        raf.close();
        return null;
    }

    public static void main(String[] args) throws IOException {
        thigk f = new thigk();
        //Khoi tao du lieu test
        String path = "review";
        String pathDestination = "reviewCopy";
        f.creatFolder(path + "/HocTap/HKI");
        f.creatFile(path + "/HocTap/HKI/ThietKeHDT.txt");
        f.creatFolder(path + "/HocTap/HKII");
        f.creatFile(path + "/HocTap/HKII/CSDL.txt");
        f.creatFolder(path + "/GiaiTri/CaNhac");
        f.creatFile(path + "/GiaiTri/CaNhac/BanLong.mp3");
        f.creatFile(path + "/GiaiTri/CaNhac/BanLong1.mp3");
        f.creatFile(path + "/GiaiTri/CaNhac/BanLong2.mp3");
        f.creatFolder(path + "/GiaiTri/HinhAnh");
        f.creatFile(path + "/GiaiTri/HinhAnh/nlp.jpg");
        f.creatFile(path + "/GiaiTri/HinhAnh/nlp1.jpg");
        f.creatFile(path + "/GiaiTri/HinhAnh/nlp2.jpg");
        f.creatFolder(path + "/Student");
        f.creatFolder(path + "/Student/FileText");
        f.creatFolder(path + "/PackFile");
        f.creatFolder(path + "/FileUnpack");
        f.creatFolder(path + "/FileUnpack/File");

        Student st1 = new Student(123, "Nguy?n Lê Phong", 8.9);
        Student st2 = new Student(1234, "Ph?m Van Lanh", 8.9);
        Student st3 = new Student(12345, "Nguy?n Lý H?i", 8.9);
        Student st4 = new Student(123456, "Hoành Danh V?ng", 8.9);
        List<Student> los = new ArrayList<>();
        los.add(st1);
        los.add(st2);
        los.add(st3);
        los.add(st4);


        Student st01 = new Student(123, "Nguy?n Lê Phong");
        Student st02 = new Student(1234, "Ph?m Van Lanh");
        Student st03 = new Student(12345, "Nguy?n Lý H?i");
        Student st04 = new Student(123456, "Hoành Danh V?ng");
        List<Student> los2 = new ArrayList<>();
        los2.add(st01);
        los2.add(st02);
        los2.add(st03);
        los2.add(st04);

        Subject ltcb = new Subject("ltcb", "L?p Trình Co B?n", "4", 9.0);
        Subject ltdt = new Subject("ltdt", "Lý Thuy?t Ð? Th?", "4", 9.0);
        Subject ltm = new Subject("ltm", "L?p Trình M?ng", "4", 9.0);
        Subject mmt = new Subject("mmt", "M?ng Máy Tính", "4", 9.0);
        Subject hdh = new Subject("hdh", "Nh?p Môn H? Ði?u Hành", "4", 9.0);

        st01.addSubject(hdh);
        st01.addSubject(ltdt);
        st01.addSubject(mmt);

        st02.addSubject(mmt);
        st02.addSubject(hdh);

        st03.addSubject(ltcb);
        st03.addSubject(ltm);

        st04.addSubject(mmt);
        st04.addSubject(ltcb);
        st04.addSubject(hdh);
        st04.addSubject(ltdt);


        //start test save and load list of student with DataStream
        f.saveListOfStudentToFile(los, path + "/Student/danh-sach-sinh-vien.txt");
        los = f.loadFileToListOfStudent(path + "/Student/danh-sach-sinh-vien.txt");
        System.out.println(los.toString());
        //end test save and load list of student with DataStream

        //start test save and load list of student from/to file text but can readable by excel software
        String delimited = "\t";
        String charset = "UNICODE";
        f.exportListOfStudentToFileText(los2, path + "/Student/FileText/student.txt", delimited, charset);
        los2 = f.importFileTextToListOfStudent(path + "/Student/FileText/student.txt", delimited, charset);
        for (Student st : los2) {
            System.out.println(st.toString2());
        }
        //start test save and load list of student from/to file text but can readable by excel software

        //start test pack and unpack one file or unpack full list of file from/to file .zip
        //you need to insert file data into folder path/PackFile. example : list of file slide Network programing of Tinh teacher
        f.packFile(path + "/PackFile/folder", path + "/PackFile/file-zip.zip", false);
        f.unpack(path + "/PackFile/file-zip.zip", path + "/FileUnpack");
        f.unpackFile(path + "/PackFile/file-zip.zip", path + "/FileUnpack/File", "Module 2- SocketProgramming.ppt");
        //end test pack and unpack one file or unpack full list of file from/to file .zip


        //start test find file by name and find file by extension
        System.out.println(f.findName(path + "/HocTap", "ThietKeHDT"));
        System.out.println(f.findExt(path, ".jpg"));
        //end test find file by name and find file by extension

        //start test show directory tree from folder
        System.out.println(f.dirTree(path));
        f.copyFile(path + "/GiaiTri/CaNhac/i-do.mp3", path + "/HocTap/CaNhacCopy.mp3");
        f.copy(path, pathDestination);
        //end test show directory tree from folder

        //start test split file and join file
        f.splitFile(path + "/GiaiTri/CaNhac/i-do.mp3", path + "/splitFile", 2000 * 1024);
        f.joinFile(path + "/splitFile", "joinFile.mp3");
        //end test split file and join file

        //start test delete file and delete folder by recursive
//		System.out.println(f.deleteFile(path+"/GiaiTri/HinhAnh/nlp.jpg"));
//		System.out.println(f.deleteFolder(path));
        //end test delete file and delete folder by recursive


    }
    /*
     *  copy : size of destination file  > source file because byte array
     */
}

//a few object others
class Student {
    int id;
    String name;
    double score;
    List<Subject> listSubject;

    public Student(int id, String name, double score) {
        super();
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public void save(DataOutputStream dos) throws IOException {
        dos.writeInt(id);
        dos.writeUTF(name);
        dos.writeDouble(score);
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.listSubject = new ArrayList<Subject>();
    }

    public Student() {
        this.listSubject = new ArrayList<Subject>();
    }

    public String lineDataStudent(String delimited) {
        String line = id + delimited + name;
        if (listSubject.size() > 0) {
            for (int i = 0; i < listSubject.size(); i++) {
                line += delimited + listSubject.get(i).id + delimited + listSubject.get(i).name + delimited + listSubject.get(i).credits + delimited + listSubject.get(i).score;
            }
        }
        return line;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSubject(Subject sub) {
        listSubject.add(sub);
    }

    public int getId() {
        return id;
    }

    public String toString2() {
        return "Student [student=" + id + ", name=" + name + ", listSubject=" + listSubject + "]";
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", score=" + score + "]\n";
    }

}

class Subject {
    String id;
    String name;
    String credits;
    double score;

    public Subject(String id, String name, String credits, double score) {
        super();
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.score = score;
    }

    public Subject() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "\n \t Subject [name=" + name + ", score=" + score + "] \n";
    }

}
