package dao;

import db.DBConnection;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Add new student
    public boolean addStudent(Student student) {
        String query = "INSERT INTO students (name, department, mark1, mark2, mark3) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, student.getName());
            pst.setString(2, student.getDepartment());
            pst.setInt(3, student.getMark1());
            pst.setInt(4, student.getMark2());
            pst.setInt(5, student.getMark3());
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // View all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getInt("mark1"),
                        rs.getInt("mark2"),
                        rs.getInt("mark3")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // Update student by ID
    public boolean updateStudent(Student student) {
        String query = "UPDATE students SET name=?, department=?, mark1=?, mark2=?, mark3=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, student.getName());
            pst.setString(2, student.getDepartment());
            pst.setInt(3, student.getMark1());
            pst.setInt(4, student.getMark2());
            pst.setInt(5, student.getMark3());
            pst.setInt(6, student.getId());
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete student by ID
    public boolean deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search by ID
    public Student getStudentById(int id) {
        String query = "SELECT * FROM students WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("department"),
                            rs.getInt("mark1"),
                            rs.getInt("mark2"),
                            rs.getInt("mark3")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Search by name
    public List<Student> searchByName(String name) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE name LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, "%" + name + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("department"),
                            rs.getInt("mark1"),
                            rs.getInt("mark2"),
                            rs.getInt("mark3")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // Search by department
    public List<Student> searchByDepartment(String department) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE department LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, "%" + department + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("department"),
                            rs.getInt("mark1"),
                            rs.getInt("mark2"),
                            rs.getInt("mark3")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // Reporting: Get total students
    public int getTotalStudents() {
        String query = "SELECT COUNT(*) FROM students";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Reporting: Average marks by department
    public List<String> getAverageMarksByDepartment() {
        List<String> reports = new ArrayList<>();
        String query = "SELECT department, AVG((mark1 + mark2 + mark3)/3) AS avg_marks FROM students GROUP BY department";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String line = "Department: " + rs.getString("department") +
                        ", Avg Marks: " + String.format("%.2f", rs.getDouble("avg_marks"));
                reports.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }

    // Reporting: Toppers list
    public List<Student> getTopStudents() {
        List<Student> toppers = new ArrayList<>();
        String query = "SELECT *, (mark1 + mark2 + mark3) as total FROM students ORDER BY total DESC LIMIT 3";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                toppers.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getInt("mark1"),
                        rs.getInt("mark2"),
                        rs.getInt("mark3")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toppers;
    }
}
