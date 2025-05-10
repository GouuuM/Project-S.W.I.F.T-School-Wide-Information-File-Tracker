package panels;

import java.util.List;
import models.SchoolData;
import models.Student;
import models.Teacher;
import utility.FileManager;
import utility.InputHelper;

public class SystemManager {
    private Student loggedInStudent = null;
    private Teacher loggedInTeacher = null;

    public SystemManager() {
        SchoolData.loadStudents();
        SchoolData.loadAssignments();
        SchoolData.loadLessons();
        SchoolData.loadAnnouncements();
        SchoolData.loadGrades();
        SchoolData.loadSubjects();
    }

    public void run() {
        while (true) {
            List<String> mainMenuOptions = List.of("Register", "Login");
            InputHelper.displayMenu("School System", mainMenuOptions);
            int choice = InputHelper.getIntInput("Choice: ");

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 0 -> {
                    SchoolData.saveStudents();
                    SchoolData.saveAssignments();
                    SchoolData.saveLessons();
                    SchoolData.saveAnnouncements();
                    SchoolData.saveGrades();
                    SchoolData.saveSubjects();
                    System.out.println("Exiting system. Goodbye!");
                    InputHelper.closeScanner();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }

            if (loggedInStudent != null) {
                Student currentStudent = SchoolData.getStudents().stream()
                        .filter(s -> s.getUsername().equals(loggedInStudent.getUsername()))
                        .findFirst()
                        .orElse(null);
                if (currentStudent != null) {
                    StudentPanel.run(currentStudent);
                }
                loggedInStudent = null;
            } else if (loggedInTeacher != null) {
                TeacherPanel.run(loggedInTeacher);
                loggedInTeacher = null;
            }
        }
    }

    private void register() {
        List<String> registerOptions = List.of("Teacher", "Student");
        InputHelper.displayMenu("Register as", registerOptions);
        int role = InputHelper.getIntInput("Choice: ");

        String username = InputHelper.getStringInput("Enter your username: ");
        String password = InputHelper.getStringInput("Enter your password: ");

        try {
            switch (role) {
                case 1 -> {
                    FileManager.addRawEntry("teachers.txt", username + "," + password);
                    System.out.println("Teacher registered successfully!");
                }
                case 2 -> {
                    String name = InputHelper.getStringInput("Enter your name: ");
                    String id = InputHelper.getStringInput("Enter your student ID: ");
                    SchoolData.getStudents().add(new Student(name, id, username, password));
                    SchoolData.saveStudents();
                    System.out.println("Student registered successfully!");
                }
                default -> System.out.println("Invalid role.");
            }
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private void login() {
        List<String> loginOptions = List.of("Teacher", "Student");
        InputHelper.displayMenu("Login as", loginOptions);
        int role = InputHelper.getIntInput("Choice: ");

        if (role == 1) {
            loggedInTeacher = TeacherLogin.login();
        } else if (role == 2) {
            loggedInStudent = StudentLogin.login();
        } else {
            System.out.println("Invalid role.");
        }
    }
}