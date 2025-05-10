package utility;

import java.io.*;
import java.util.*;

public class FileManager {
    public static void addEntry(String filename, Scanner sc, String prompt) throws IOException {
        System.out.print(prompt);
        String entry = sc.nextLine();
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(entry + "\n");
        }
    }

    public static void addRawEntry(String filename, String entry) throws IOException {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(entry + "\n");
        }
    }

    public static void removeEntry(String filename, Scanner sc, String prompt) throws IOException {
        System.out.print(prompt);
        String toRemove = sc.nextLine();
        File inputFile = new File(filename);
        File tempFile = new File("temp.txt");

        try (Scanner fileScanner = new Scanner(inputFile);
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.toLowerCase().contains(toRemove.toLowerCase())) {
                    writer.println(line);
                }
            }
        }

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public static void viewFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
    }

    public static boolean validateCredentials(String filename, String username, String password) throws IOException {
        System.out.println("Validating credentials in file: " + filename + " for user: " + username);
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File does not exist: " + filename);
            return false;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println("Checking line: " + line);
                String[] parts = line.split(",");
                if (parts.length == 2 && filename.equals("teachers.txt")) {
                    String fileUsername = parts[0].trim();
                    String filePassword = parts[1].trim();
                    System.out.println("Comparing (Teacher): " + fileUsername + ":" + filePassword + " with " + username + ":" + password);
                    if (fileUsername.equals(username.trim()) && filePassword.equals(password.trim())) {
                        System.out.println("Credentials match!");
                        return true;
                    }
                } else if (parts.length >= 4 && filename.equals("students_data.txt")) {  // Correct student check
                    String fileUsername = parts[2].trim();
                    String filePassword = parts[3].trim();
                    System.out.println("Comparing (Student): " + fileUsername + ":" + filePassword + " with " + username + ":" + password);
                    if (fileUsername.equals(username.trim()) && filePassword.equals(password.trim())) {
                        System.out.println("Credentials match!");
                        return true;
                    }
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        }
        System.out.println("Credentials do not match.");
        return false;
    }

    public static List<String> readFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(filename);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    public static void writeFile(String filename, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}