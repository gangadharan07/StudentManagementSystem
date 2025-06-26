package model;

public class Person {
    protected String name;
    protected String department;

    public Person(String name, String department) {
        this.name = name;
        this.department = department;
    }

    // Common getters and setters
    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
