package ConnectJDBC;

public class Student {
    private String name;
    private int id, score, age;

    public Student( int id,String name, int score, int age) {
        this.name = name;
        this.id = id;
        this.score = score;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                " id=" + id +
                ", name='" + name+
                ", score=" + score +
                ", age=" + age +
                '}';
    }
}
