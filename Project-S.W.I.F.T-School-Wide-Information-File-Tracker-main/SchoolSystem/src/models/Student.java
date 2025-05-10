package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utility.FileManager;
import utility.InputHelper;

public class Student extends User {
    private String name;
    private String id;
    private List<Assignment> assignments;
    private List<String> enrolledSubjects;

    public Student(String username, String password) {
        super(username, password);
        this.name = "N/A";
        this.id = "N/A";
        this.assignments = new ArrayList<>();
        this.enrolledSubjects = new ArrayList<>();
    }

    public Student(String name, String id, String username, String password) {
        super(username, password);
        this.name = name;
        this.id = id;
        this.assignments = new ArrayList<>();
        this.enrolledSubjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public List<String> getEnrolledSubjects() {
        return enrolledSubjects;
    }

    public void viewEnrolledSubjects() {
        if (enrolledSubjects.isEmpty()) {
            System.out.println("You are not currently enrolled in any subjects.");
        } else {
            System.out.println("\n--- Enrolled Subjects ---");
            for (String subject : enrolledSubjects) {
                System.out.println(subject);
            }
        }
    }

    public boolean handleChoice(int choice, Scanner sc) {
        try {
            switch (choice) {
                case 1 -> {
                    String subjectCode = InputHelper.getStringInput("Enter subject code to enroll: ");
                    if (!enrolledSubjects.contains(subjectCode)) {
                        enrolledSubjects.add(subjectCode);
                        System.out.println("Successfully enrolled in subject: " + subjectCode);
                    } else {
                        System.out.println("Already enrolled in this subject.");
                    }
                }
                case 2 -> {
                    String answer = InputHelper.getStringInput("Enter your answer for assignment: ");
                    FileManager.addEntry("submitted_assignments_" + getUsername() + ".txt", sc, "Answer: " + answer);
                }
                case 3 -> {
                    String assignmentToRemove = InputHelper.getStringInput("Enter assignment title to remove: ");
                    FileManager.removeEntry("submitted_assignments_" + getUsername() + ".txt", sc, "Remove: " + assignmentToRemove);
                }
                case 4 -> FileManager.viewFile("grades_" + getUsername() + ".txt");
                case 5 -> FileManager.viewFile("lessonplans.txt");
                case 6 -> FileManager.viewFile("announcements.txt");
                case 7 -> FileManager.viewFile("grades_" + getUsername() + ".txt");
                case 8 -> viewEnrolledSubjects();
                case 0 -> {
                    return false;
                }
                default -> System.out.println("Invalid choice.");
            }
        } catch (IOException e) {
            System.out.println("Error handling file: " + e.getMessage());
        }
        return true;
    }
}