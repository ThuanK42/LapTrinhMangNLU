package Tuan_8;

import java.util.ArrayList;

public class FileServer {
    private static ArrayList<Student> listStudents = new ArrayList<>();

    // lay du lieu
    public static ArrayList<Student> getDatabase() {
        listStudents.add(new Student("Loc Phu Ho", 24, 9.2));
        listStudents.add(new Student("Phuc XO", 30, 5.2));
        listStudents.add(new Student("Kha Banh", 15, 3.2));
        listStudents.add(new Student("Huan Hoa Hong", 17, 1.2));
        listStudents.add(new Student("Duong Minh Tuyen", 28, 7.2));
        return listStudents;
    }

    // tim ds so sanh ten
    public String findByName(String name) {
        String result = "";
        ArrayList<Student> list = getDatabase();
        for (Student student : list) {
            if (student.getName().equalsIgnoreCase(name)) {
                result += student.toString();
            }
        }
        return result;
    }

    // tim ds so sanh tuoi
    public String findByLessAge(int age) {
        String result = "";
        ArrayList<Student> list = getDatabase();
        for (Student student : list) {
            if (student.getAge() <= age) {
                result += student.toString();
            }
        }
        return result;
    }

    // tim ds so sanh diem
    public String findByLessScore(double score) {
        String result = "";
        ArrayList<Student> list = getDatabase();
        for (Student student : list) {
            if (student.getScore() <= score) {
                result += student.toString();
            }
        }
        return result;
    }
}
