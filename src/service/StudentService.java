package service;

import dao.StudentDAO;
import model.Student;
import utils.InputValidator;

import java.util.List;
import java.util.Scanner;

public class StudentService {
    private StudentDAO dao = new StudentDAO();
    private Scanner scanner = new Scanner(System.in);

    // Add new student
    public void addStudent() {
        String name, dept;
        int m1, m2, m3;

        // Validate name
        do {
            System.out.print("Enter student name: ");
            name = scanner.nextLine();
            if (!InputValidator.isValidName(name)) {
                System.out.println("❌ Invalid name. Only letters and spaces allowed.");
            }
        } while (!InputValidator.isValidName(name));

        // Validate department
        do {
            System.out.print("Enter department: ");
            dept = scanner.nextLine();
            if (!InputValidator.isValidDepartment(dept)) {
                System.out.println("❌ Department cannot be empty.");
            }
        } while (!InputValidator.isValidDepartment(dept));

        // Validate marks
        m1 = readValidMark("Enter mark1: ");
        m2 = readValidMark("Enter mark2: ");
        m3 = readValidMark("Enter mark3: ");

        Student student = new Student(name, dept, m1, m2, m3);
        boolean success = dao.addStudent(student);

        if (success) {
            System.out.println("✅ Student added successfully!");
        } else {
            System.out.println("❌ Error adding student.");
        }
    }

    // Helper method to validate marks input
    private int readValidMark(String prompt) {
        int mark;
        while (true) {
            try {
                System.out.print(prompt);
                mark = Integer.parseInt(scanner.nextLine());
                if (InputValidator.isValidMark(mark)) {
                    return mark;
                } else {
                    System.out.println("❌ Mark must be between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }

    // View all students
    public void viewAllStudents() {
        List<Student> list = dao.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-6s %-6s %-6s %-6s%n", "ID", "Name", "Department", "M1", "M2", "M3", "Total");
        for (Student s : list) {
            System.out.printf("%-5d %-20s %-15s %-6d %-6d %-6d %-6d%n",
                    s.getId(), s.getName(), s.getDepartment(),
                    s.getMark1(), s.getMark2(), s.getMark3(), s.getTotalMarks());
        }
    }

    // Update student by ID
    public void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Student existing = dao.getStudentById(id);
        if (existing == null) {
            System.out.println("❌ Student not found.");
            return;
        }

        System.out.print("Enter new name [" + existing.getName() + "]: ");
        String name = scanner.nextLine();
        if (name.isEmpty() || !InputValidator.isValidName(name)) name = existing.getName();

        System.out.print("Enter new department [" + existing.getDepartment() + "]: ");
        String dept = scanner.nextLine();
        if (dept.isEmpty() || !InputValidator.isValidDepartment(dept)) dept = existing.getDepartment();

        System.out.print("Enter new mark1 [" + existing.getMark1() + "]: ");
        String m1s = scanner.nextLine();
        int m1 = m1s.isEmpty() ? existing.getMark1() : parseValidMark(m1s, existing.getMark1());

        System.out.print("Enter new mark2 [" + existing.getMark2() + "]: ");
        String m2s = scanner.nextLine();
        int m2 = m2s.isEmpty() ? existing.getMark2() : parseValidMark(m2s, existing.getMark2());

        System.out.print("Enter new mark3 [" + existing.getMark3() + "]: ");
        String m3s = scanner.nextLine();
        int m3 = m3s.isEmpty() ? existing.getMark3() : parseValidMark(m3s, existing.getMark3());

        Student updated = new Student(id, name, dept, m1, m2, m3);
        boolean success = dao.updateStudent(updated);

        if (success) {
            System.out.println("✅ Student updated successfully!");
        } else {
            System.out.println("❌ Update failed.");
        }
    }

    private int parseValidMark(String input, int fallback) {
        try {
            int mark = Integer.parseInt(input);
            return InputValidator.isValidMark(mark) ? mark : fallback;
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    // Delete student by ID
    public void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean success = dao.deleteStudent(id);

        if (success) {
            System.out.println("✅ Student deleted successfully.");
        } else {
            System.out.println("❌ Student not found or deletion failed.");
        }
    }

    // Search student by ID
    public void searchById() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student student = dao.getStudentById(id);

        if (student != null) {
            System.out.println("Student Found:");
            printStudentDetails(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Search student by name
    public void searchByName() {
        System.out.print("Enter name keyword: ");
        String name = scanner.nextLine();
        List<Student> list = dao.searchByName(name);
        if (list.isEmpty()) {
            System.out.println("No results.");
        } else {
            list.forEach(this::printStudentDetails);
        }
    }

    // Search by department
    public void searchByDepartment() {
        System.out.print("Enter department: ");
        String dept = scanner.nextLine();
        List<Student> list = dao.searchByDepartment(dept);
        if (list.isEmpty()) {
            System.out.println("No results.");
        } else {
            list.forEach(this::printStudentDetails);
        }
    }

    // Report: total students
    public void reportTotalStudents() {
        int count = dao.getTotalStudents();
        System.out.println("Total number of students: " + count);
    }

    // Report: average marks by department
    public void reportAvgMarksByDepartment() {
        List<String> report = dao.getAverageMarksByDepartment();
        if (report.isEmpty()) {
            System.out.println("No data available.");
        } else {
            report.forEach(System.out::println);
        }
    }

    // Report: toppers list
    public void reportTopStudents() {
        List<Student> topList = dao.getTopStudents();
        if (topList.isEmpty()) {
            System.out.println("No records.");
        } else {
            System.out.println("Top Performing Students:");
            topList.forEach(this::printStudentDetails);
        }
    }

    // Utility method to print a student's details
    private void printStudentDetails(Student s) {
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Department: " + s.getDepartment());
        System.out.println("Marks: " + s.getMark1() + ", " + s.getMark2() + ", " + s.getMark3());
        System.out.println("Total: " + s.getTotalMarks());
        System.out.println("Average: " + s.getAverageMarks());
        System.out.println("---------------------------");
    }
}
