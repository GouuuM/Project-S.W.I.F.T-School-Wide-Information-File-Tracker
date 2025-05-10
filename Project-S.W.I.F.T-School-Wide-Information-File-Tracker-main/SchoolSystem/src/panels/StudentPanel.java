package panels;

import java.util.List;
import models.Student;
import utility.InputHelper;

public class StudentPanel {
    public static void run(Student student) {
        while (true) {
            List<String> studentMenuOptions = List.of(
                    "Enroll in Subject",
                    "View Enrolled Subjects",
                    "Answer Assignment",
                    "Remove Assignment",
                    "View Graded Assignments",
                    "View Lesson Plan",
                    "View Announcements",
                    "View Grades"
            );
            InputHelper.displayMenu("Student Panel - " + student.getUsername(), studentMenuOptions);
            int choice = InputHelper.getIntInput("Choice: ");

            if (!student.handleChoice(choice, InputHelper.getScanner())) {
                break;
            }
        }
    }
}