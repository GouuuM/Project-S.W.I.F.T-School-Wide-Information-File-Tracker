package models;
import java.util.Scanner;

public abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean login(Scanner sc) {
        System.out.print("Enter username: ");
        String enteredUsername = sc.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = sc.nextLine();
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }
}