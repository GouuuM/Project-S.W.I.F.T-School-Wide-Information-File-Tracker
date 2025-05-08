package panels;
import models.Student;
import utility.FileManager;
import utility.InputHelper;

public class StudentLogin {
    public static Student login() {
        String username = InputHelper.getStringInput("Enter username: ");
        String password = InputHelper.getStringInput("Enter password: ");
        try {
            if (FileManager.validateCredentials("students_data.txt", username, password)) {
                return new Student(username, password);
            } else {
                System.out.println("Invalid credentials.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}