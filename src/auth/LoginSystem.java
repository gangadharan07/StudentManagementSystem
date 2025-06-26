package auth;

import dao.UserDAO;
import model.User;

import java.util.Scanner;

public class LoginSystem {
    public boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("===== Admin Login =====");
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        User loggedInUser = userDAO.login(user, pass);

        if (loggedInUser != null) {
            System.out.println("✅ Login successful. Welcome, " + loggedInUser.getUsername() + "!");
            System.out.println("Role: " + loggedInUser.getRole());
            return true;
        } else {
            System.out.println("❌ Invalid credentials. Access denied.");
            return false;
        }
    }
}
