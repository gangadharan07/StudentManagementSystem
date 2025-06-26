package model;

public class Student extends Person {
    private int id;
    private int mark1, mark2, mark3;

    public Student(int id, String name, String department, int mark1, int mark2, int mark3) {
        super(name, department);
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    public Student(String name, String department, int mark1, int mark2, int mark3) {
        super(name, department);
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    public int getId() {
        return id;
    }

    public int getMark1() {
        return mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public int getMark3() {
        return mark3;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    public int getTotalMarks() {
        return mark1 + mark2 + mark3;
    }

    public double getAverageMarks() {
        return (mark1 + mark2 + mark3) / 3.0;
    }
}
