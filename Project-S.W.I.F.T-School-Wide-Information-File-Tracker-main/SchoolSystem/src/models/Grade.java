package models;

public class Grade {
    private String studentId;
    private String score;

    public Grade(String studentId, String score) {
        this.studentId = studentId;
        this.score = score;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Grade: " + score;
    }
}