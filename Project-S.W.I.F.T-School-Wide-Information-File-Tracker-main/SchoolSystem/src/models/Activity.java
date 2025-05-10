package models;

public class Activity {
    String title; // Name of the activity
    String instructions; // How to do the activity
    String answer; // Student's response
    String grade; // Evaluation of the answer

    // Creates an activity with a title and instructions.
    public Activity(String title, String instructions) {
        this.title = title;
        this.instructions = instructions;
    }

    // Gets the activity's name.
    public String getTitle() {
        return title;
    }

    // Gets the activity's instructions.
    public String getInstructions() {
        return instructions;
    }

    // Sets the student's answer.
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Sets the grade for the activity.
    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Gets the grade for the activity.
    public String getGrade() {
        return grade;
    }
}