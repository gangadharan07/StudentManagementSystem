# 🧑‍🎓 Student Management System (Java + MySQL)

A simple yet complete **console-based Student Management System** built using **Core Java**, **JDBC**, and **MySQL**. This project is designed for academic purposes and follows object-oriented principles including **inheritance**, **file handling**, **input validation**, and **modular design**.

---

## 📌 Features

### ✅ Student Module
- Add New Student
- View All Student Details
- Update Student Record
- Delete Student Record

### 🔍 Search Module
- Search by ID
- Search by Name
- Search by Department

### 📈 Reporting Module
- Total Number of Students
- Average Marks by Department
- Top Performing Students

### 💾 File Handling
- Export all student data to a `.txt` file (for backup)

### 🛢 Database Integration (MySQL + JDBC)
- All student data is stored and retrieved using **MySQL**
- Secure connection using **JDBC**
- Input validation using custom utility class

### 🔐 Login System
- Basic admin login authentication using Java
- Extendable for multi-role user login

---

## 🧱 Technologies Used

- Java (Core Concepts, OOP, Scanner, Collections)
- JDBC (MySQL Driver)
- MySQL (Backend)
- Eclipse/IntelliJ (IDE)
- Git & GitHub (Version Control)

---

## 🗃️ Database Structure

This system uses **3 main tables** in the MySQL database:

### 1. `students`
Stores all student-related academic information.

| Field         | Type         |
|---------------|--------------|
| `id`          | INT (PK)     |
| `name`        | VARCHAR(100) |
| `department_id` | INT (FK → departments.id) |
| `mark1`       | INT          |
| `mark2`       | INT          |
| `mark3`       | INT          |

---

### 2. `departments`
Maintains normalized list of departments.

| Field     | Type         |
|-----------|--------------|
| `id`      | INT (PK)     |
| `name`    | VARCHAR(50)  |

---

### 3. `users`
Handles authentication for admin or other roles.

| Field     | Type         |
|-----------|--------------|
| `id`      | INT (PK)     |
| `username`| VARCHAR(50)  |
| `password`| VARCHAR(50)  |
| `role`    | VARCHAR(20)  |

---

## 🔗 Entity Relationships

- `students.department_id` → `departments.id` (**Many-to-One**)
- `users` is an independent entity for login system
- `Student` class **inherits** from `Person` class (demonstrating Java inheritance)

---

