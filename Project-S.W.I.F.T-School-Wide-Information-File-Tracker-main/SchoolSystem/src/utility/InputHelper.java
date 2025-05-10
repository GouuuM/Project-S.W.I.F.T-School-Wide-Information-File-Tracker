package utility;

import java.util.List;
import java.util.Scanner;

public class InputHelper {
    private static final Scanner sc = new Scanner(System.in); // Creates a single Scanner object for the whole program.

    // Returns the program's Scanner object, so other parts of the code can get user input.
    public static Scanner getScanner() {
        return sc;
    }

    // Closes the program's Scanner object to release system resources. Should be done when the program ends.
    public static void closeScanner() {
        sc.close();
    }

    // Prompts the user for an integer and ensures valid integer input.
    public static int getIntInput(String prompt) {
        System.out.print(prompt); // Display the prompt to the user.
        while (!sc.hasNextInt()) { // Loop until a valid integer is entered.
            System.out.println("Invalid input. Please enter a number.");
            System.out.print(prompt);
            sc.next(); // Consume the invalid input.
        }
        int choice = sc.nextInt(); // Read the valid integer.
        sc.nextLine(); // Consume the newline character left by nextInt().
        return choice; // Return the entered integer.
    }

    // Prompts the user for a string and returns the input.
    public static String getStringInput(String prompt) {
        System.out.print(prompt); // Display the prompt to the user.
        return sc.nextLine(); // Read the entire line of input as a string.
    }

    // Displays a numbered menu to the user.
    public static void displayMenu(String title, List<String> options) {
        System.out.println("\n--- " + title + " ---"); // Print the menu title.
        for (int i = 0; i < options.size(); i++) { // Loop through the menu options.
            System.out.println((i + 1) + ". " + options.get(i)); // Print each option with its number.
        }
        System.out.println("0. Logout"); // Print the logout option.
        System.out.print("Choice: "); // Prompt the user for their choice.
    }
}