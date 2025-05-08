package panels;
import models.Teacher;
import utility.FileManager;
import utility.InputHelper;

public class TeacherLogin {
    public static Teacher login() {
        String username = InputHelper.getStringInput("Enter username: ");
        String password = InputHelper.getStringInput("Enter password: ");
        try {
            if (FileManager.validateCredentials("teachers.txt", username, password)) {
                return new Teacher(username, password);
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