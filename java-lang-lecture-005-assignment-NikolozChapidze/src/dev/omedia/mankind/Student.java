package dev.omedia.mankind;

public class Student extends Person{

    private String schoolName;

    public Student(String personalNo, String schoolName) {
        super(personalNo);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
