package panels;
import java.util.List;
import models.*;
import utility.InputHelper;

public class TeacherPanel {
    public static void run(Teacher teacher) {
        while (true) {
            List<String> teacherMenuOptions = List.of(
                    "Add Student",
                    "Remove Student",
                    "View Students",
                    "Give Assignment / Activity",
                    "Remove Assignment",
                    "Remove Activity",
                    "Add Lesson Plan",
                    "Remove Lesson Plan",
                    "Grade Student",
                    "Remove Student Grade",
                    "Add Announcement",
                    "Remove Announcement"
            );
            InputHelper.displayMenu("Teacher Panel - " + teacher.getUsername(), teacherMenuOptions);
            int choice = InputHelper.getIntInput("Choice: ");

            switch (choice) {
                case 1 ->  {
                    String name = InputHelper.getStringInput("Enter student name: ");
                    String id = InputHelper.getStringInput("Enter student ID: ");
                    SchoolData.getStudents().add(new Student(name, id, "", ""));
                    SchoolData.saveStudents();
                    System.out.println("Student added (username and password not set).");
                }
                case 2 ->  {
                    String idToRemove = InputHelper.getStringInput("Enter student ID to remove: ");
                    SchoolData.getStudents().removeIf(s -> s.getId().equals(idToRemove));
                    SchoolData.saveStudents();
                    System.out.println("Student removed.");
                }
                case 3 -> viewStudents();
                case 4 ->  {
                    System.out.println("\n--- Give Assignment or Activity ---");
                    int typeChoice = InputHelper.getIntInput("1. Give Assignment\n2. Give Activity\nChoice: ");
                    if (typeChoice == 1) {
                        String title = InputHelper.getStringInput("Enter assignment title: ");
                        String desc = InputHelper.getStringInput("Enter assignment description: ");
                        SchoolData.getAssignments().add(new Assignment(title, desc));
                        SchoolData.saveAssignments();
                        System.out.println("Assignment given.");
                    } else if (typeChoice == 2) {
                        String title = InputHelper.getStringInput("Enter activity title: ");
                        String instructions = InputHelper.getStringInput("Enter activity instructions: ");
                        SchoolData.getActivities().add(new Activity(title, instructions));
                        SchoolData.saveActivities();
                        System.out.println("Activity given.");
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                case 5 ->  {
                    String titleToRemove = InputHelper.getStringInput("Enter title of assignment to remove: ");
                    SchoolData.getAssignments().removeIf(a -> a.getTitle().equalsIgnoreCase(titleToRemove));
                    SchoolData.saveAssignments();
                    System.out.println("Assignment removed.");
                }
                case 6 ->  {
                    String titleToRemove = InputHelper.getStringInput("Enter title of activity to remove: ");
                    SchoolData.getActivities().removeIf(a -> a.getTitle().equalsIgnoreCase(titleToRemove));
                    SchoolData.saveActivities();
                    System.out.println("Activity removed.");
                }
                case 7 ->  {
                    String title = InputHelper.getStringInput("Enter lesson title: ");
                    String content = InputHelper.getStringInput("Enter lesson content: ");
                    SchoolData.getLessons().add(new LessonPlan(title, content));
                    SchoolData.saveLessons();
                    System.out.println("Lesson added.");
                }
                case 8 ->  {
                    String titleToRemove = InputHelper.getStringInput("Enter lesson title to remove: ");
                    SchoolData.getLessons().removeIf(l -> l.getTitle().equalsIgnoreCase(titleToRemove));
                    SchoolData.saveLessons();
                    System.out.println("Lesson removed.");
                }
                case 9 ->  {
                    String studentIdToGrade = InputHelper.getStringInput("Enter student ID to grade: ");
                    String finalGrade = InputHelper.getStringInput("Enter grade: ");
                    SchoolData.getGrades().add(new Grade(studentIdToGrade, "Overall: " + finalGrade));
                    SchoolData.saveGrades();
                    System.out.println("Student graded.");
                }
                case 10 ->  {
                    String studentIdToRemoveGrade = InputHelper.getStringInput("Enter student ID to remove grade for: ");
                    SchoolData.getGrades().removeIf(g -> g.getStudentId().equals(studentIdToRemoveGrade));
                    SchoolData.saveGrades();
                    System.out.println("Student grade removed.");
                }
                case 11 ->  {
                    String message = InputHelper.getStringInput("Enter announcement: ");
                    SchoolData.getAnnouncements().add(new Announcement(message));
                    SchoolData.saveAnnouncements();
                    System.out.println("Announcement added.");
                }
                case 12 ->  {
                    String messageToRemove = InputHelper.getStringInput("Enter message to remove: ");
                    SchoolData.getAnnouncements().removeIf(a -> a.getMessage().equalsIgnoreCase(messageToRemove));
                    SchoolData.saveAnnouncements();
                    System.out.println("Announcement removed.");
                }
                case 0 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewStudents() {
        List<Student> allStudents = SchoolData.getStudents();
        if (allStudents.isEmpty()) {
            System.out.println("No students registered in the system.");
        } else {
            System.out.println("\n--- Registered Students ---");
            for (Student student : allStudents) {
                System.out.println("Name: " + student.getName() + ", ID: " + student.getId());
            }
        }
    }
}