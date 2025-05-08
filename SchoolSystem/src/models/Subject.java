package models;

public class Subject {
    private String code;
    private String name;
    private String description;

    public Subject(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Subject Code: " + code + "\nName: " + name + "\nDescription: " + description;
    }
}