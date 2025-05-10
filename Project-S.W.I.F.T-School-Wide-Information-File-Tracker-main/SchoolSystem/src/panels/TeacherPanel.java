package panels;

import java.util.List;
import models.Announcement;
import models.Assignment;
import models.Lesson;
import models.SchoolData;
import models.Subject;
import models.Teacher;
import utility.InputHelper;

public class TeacherPanel {
    public static void run(Teacher teacher) {
        System.out.println("\n--- Teacher Panel ---");
        System.out.println("Welcome, Teacher: " + teacher.getUsername());

        while (true) {
            List<String> options = List.of(
                    "View Students",
                    "Add Assignment",
                    "View Assignments",
                    "Add Lesson",
                    "View Lessons",
                    "Add Announcement",
                    "View Announcements",
                    "Add Grade",
                    "View Grades",
                    "Add Subject",
                    "View Subjects"
            );
            InputHelper.displayMenu("Teacher Options", options);
            int choice = InputHelper.getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> viewStudents();
                case 2 -> addAssignment();
                case 3 -> viewAssignments();
                case 4 -> addLesson();
                case 5 -> viewLessons();
                case 6 -> addAnnouncement();
                case 7 -> viewAnnouncements();
                case 8 -> addGrade();
                case 9 -> viewGrades();
                case 10 -> addSubject();
                case 11 -> viewSubjects();
                case 0 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewStudents() {
        if (SchoolData.getStudents().isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        System.out.println("\n--- Registered Students ---");
        for (models.Student student : SchoolData.getStudents()) {
            System.out.println("Name: " + student.getName() + ", ID: " + student.getId() + ", Username: " + student.getUsername());
        }
    }

    private static void addAssignment() {
        System.out.println("\n--- Add New Assignment ---");
        String title = InputHelper.getStringInput("Enter assignment title: ");
        String instructions = InputHelper.getStringInput("Enter instructions: ");
        SchoolData.getAssignments().add(new Assignment(title, instructions));
        SchoolData.saveAssignments();
        System.out.println("Assignment added successfully!");
    }

    private static void viewAssignments() {
        if (SchoolData.getAssignments().isEmpty()) {
            System.out.println("No assignments created yet.");
            return;
        }
        System.out.println("\n--- Assignments ---");
        for (Assignment assignment : SchoolData.getAssignments()) {
            System.out.println("Title: " + assignment.getTitle() + ", Instructions: " + assignment.getInstructions());
        }
    }

    private static void addLesson() {
        System.out.println("\n--- Add New Lesson ---");
        String title = InputHelper.getStringInput("Enter lesson title: ");
        String content = InputHelper.getStringInput("Enter lesson content: ");
        SchoolData.getLessons().add(new Lesson(title, content));
        SchoolData.saveLessons();
        System.out.println("Lesson added successfully!");
    }

    private static void viewLessons() {
        if (SchoolData.getLessons().isEmpty()) {
            System.out.println("No lessons created yet.");
            return;
        }
        System.out.println("\n--- Lessons ---");
        for (Lesson lesson : SchoolData.getLessons()) {
            System.out.println("Title: " + lesson.getTitle() + "\nContent: " + lesson.getContent());
        }
    }

    private static void addAnnouncement() {
        System.out.println("\n--- Add New Announcement ---");
        String message = InputHelper.getStringInput("Enter announcement message: ");
        SchoolData.getAnnouncements().add(new Announcement(message));
        SchoolData.saveAnnouncements();
        System.out.println("Announcement added successfully!");
    }

    private static void viewAnnouncements() {
        if (SchoolData.getAnnouncements().isEmpty()) {
            System.out.println("No announcements made yet.");
            return;
        }
        System.out.println("\n--- Announcements ---");
        for (Announcement announcement : SchoolData.getAnnouncements()) {
            System.out.println(announcement.getMessage());
        }
    }

    private static void addGrade() {
        System.out.println("\n--- Add Grade ---");
        String studentId = InputHelper.getStringInput("Enter student ID to grade: ");
        models.Student student = SchoolData.findStudentById(studentId);
        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }
        String score = InputHelper.getStringInput("Enter grade/score for " + student.getName() + ": ");
        SchoolData.getGrades().add(new models.Grade(studentId, score));
        SchoolData.saveGrades();
        System.out.println("Grade added successfully for student " + student.getName() + ".");
    }

    private static void viewGrades() {
        if (SchoolData.getGrades().isEmpty()) {
            System.out.println("No grades recorded yet.");
            return;
        }
        System.out.println("\n--- Grades ---");
        for (models.Grade grade : SchoolData.getGrades()) {
            models.Student student = SchoolData.findStudentById(grade.getStudentId());
            String studentName = (student != null) ? student.getName() : "Unknown Student";
            System.out.println("Student ID: " + grade.getStudentId() + " (" + studentName + "), Grade/Score: " + grade.getScore());
        }
    }

    private static void addSubject() {
        System.out.println("\n--- Add New Subject ---");
        String codeAndName = InputHelper.getStringInput("Enter subject code and name (e.g., MATH101,Calculus): ");
        String[] parts = codeAndName.split(",");
        if (parts.length == 2) {
            String code = parts[0].trim();
            String name = parts[1].trim();
            String description = InputHelper.getStringInput("Enter subject description: ");
            SchoolData.getSubjects().add(new Subject(code, name, description));
            SchoolData.saveSubjects();
            System.out.println("Subject added successfully!");
        } else {
            System.out.println("Invalid input format. Please enter code and name separated by a comma.");
        }
    }

    private static void viewSubjects() {
        if (SchoolData.getSubjects().isEmpty()) {
            System.out.println("No subjects added yet.");
            return;
        }
        System.out.println("\n--- Subjects ---");
        for (Subject subject : SchoolData.getSubjects()) {
            System.out.println(subject);
        }
    }
}