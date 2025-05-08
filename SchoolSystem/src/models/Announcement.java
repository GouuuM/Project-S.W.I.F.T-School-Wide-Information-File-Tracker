package models;

public class Announcement {
    private String message;

    public Announcement(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Announcement: " + message;
    }
}