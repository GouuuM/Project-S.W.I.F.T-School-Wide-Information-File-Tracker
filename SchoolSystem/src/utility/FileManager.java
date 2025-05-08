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
            System.out.println("No entries found in " + filename);
            return;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
    }

    public static boolean validateCredentials(String filename, String username, String password) throws IOException {
        File file = new File(filename);
        if (!file.exists()) return false;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String fileUsername = parts[2].trim();
                    String filePassword = parts[3].trim();
                    if (fileUsername.equals(username.trim()) && filePassword.equals(password.trim())) {
                        return true;
                    }
                }
            }
        }
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