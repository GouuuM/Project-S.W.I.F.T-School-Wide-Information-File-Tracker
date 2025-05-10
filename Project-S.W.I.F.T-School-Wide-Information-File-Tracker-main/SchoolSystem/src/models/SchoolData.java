package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import utility.FileManager;

public class SchoolData {
    public static List<Activity> activities = new ArrayList<>();
    public static List<Announcement> announcements = new ArrayList<>();
    public static List<Assignment> assignments = new ArrayList<>();
    public static List<Grade> grades = new ArrayList<>();
    public static List<Lesson> lessons = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();
    public static List<Subject> subjects = new ArrayList<>(); // Added subjects list

    private static final String STUDENTS_FILE = "students_data.txt";
    private static final String ASSIGNMENTS_FILE = "assignments_data.txt";
    private static final String LESSONS_FILE = "lessons_data.txt";
    private static final String ANNOUNCEMENTS_FILE = "announcements.txt";
    private static final String GRADES_FILE = "grades_data.txt";
    private static final String ACTIVITIES_FILE = "activities.txt";
    private static final String SUBJECTS_FILE = "subjects_data.txt"; // Added subjects file

    // Static initializer to load initial subjects
    static {
        loadSubjects(); // Load subjects from file when the class is loaded
    }

    private static <T> void loadData(String filename, Function<String[], T> mapper, List<T> targetList) {
        targetList.clear();
        try {
            List<String> lines = FileManager.readFile(filename);
            for (String line : lines) {
                String[] parts = line.split(",");
                T item = mapper.apply(parts);
                if (item != null) {
                    targetList.add(item);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data from " + filename + ": " + e.getMessage());
        }
    }

    private static <T> void saveData(String filename, Function<T, String> mapper, List<T> sourceList) {
        List<String> lines = new ArrayList<>();
        for (T item : sourceList) {
            lines.add(mapper.apply(item));
        }
        try {
            FileManager.writeFile(filename, lines);
        } catch (IOException e) {
            System.out.println("Error saving data to " + filename + ": " + e.getMessage());
        }
    }

    public static void loadStudents() {
        loadData(STUDENTS_FILE, parts ->
                        parts.length == 4 ? new Student(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()) : null,
                students);
    }

    public static void saveStudents() {
        saveData(STUDENTS_FILE, student ->
                        student.getName() + "," + student.getId() + "," + student.getUsername() + "," + student.getPassword(),
                students);
    }

    public static void loadAssignments() {
        loadData(ASSIGNMENTS_FILE, parts ->
                        parts.length == 2 ? new Assignment(parts[0].trim(), parts[1].trim()) : null,
                assignments);
    }

    public static void saveAssignments() {
        saveData(ASSIGNMENTS_FILE, assignment ->
                        assignment.getTitle() + "," + assignment.getInstructions(),
                assignments);
    }

    public static void loadLessons() {
        loadData(LESSONS_FILE, parts ->
                        parts.length == 2 ? new Lesson(parts[0].trim(), parts[1].trim()) : null,
                lessons);
    }

    public static void saveLessons() {
        saveData(LESSONS_FILE, lesson ->
                        lesson.getTitle() + "," + lesson.getContent(),
                lessons);
    }

    public static void loadAnnouncements() {
        loadData(ANNOUNCEMENTS_FILE, parts ->
                        parts.length == 1 ? new Announcement(parts[0].trim()) : null,
                announcements);
    }

    public static void saveAnnouncements() {
        saveData(ANNOUNCEMENTS_FILE, announcement ->
                        announcement.getMessage(),
                announcements);
    }

    public static void loadGrades() {
        loadData(GRADES_FILE, parts ->
                        parts.length == 2 ? new Grade(parts[0].trim(), parts[1].trim()) : null,
                grades);
    }

    public static void saveGrades() {
        saveData(GRADES_FILE, grade ->
                        grade.getStudentId() + "," + grade.getScore(),
                grades);
    }

    public static void loadActivities() {
        loadData(ACTIVITIES_FILE, parts ->
                        parts.length == 2 ? new Activity(parts[0].trim(), parts[1].trim()) : null,
                activities);
    }

    public static void saveActivities() {
        saveData(ACTIVITIES_FILE, activity ->
                        activity.getTitle() + "," + activity.getInstructions(),
                activities);
    }

    public static void loadSubjects() {
        loadData(SUBJECTS_FILE, parts ->
                        parts.length == 3 ? new Subject(parts[0].trim(), parts[1].trim(), parts[2].trim()) : null,
                subjects);
    }

    public static void saveSubjects() {
        saveData(SUBJECTS_FILE, subject ->
                        subject.getCode() + "," + subject.getName() + "," + subject.getDescription(),
                subjects);
    }

    public static Student findStudentById(String id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static List<Assignment> getAssignments() {
        return assignments;
    }

    public static List<Lesson> getLessons() {
        return lessons;
    }

    public static List<Announcement> getAnnouncements() {
        return announcements;
    }

    public static List<Grade> getGrades() {
        return grades;
    }

    public static List<Activity> getActivities() {
        return activities;
    }

    public static List<Subject> getSubjects() {
        return subjects;
    }
}