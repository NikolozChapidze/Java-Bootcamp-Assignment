package dev.omedia.mankind;

import dev.omedia.education.University;

public class HighSchoolStudent extends Student{
    private int grade;

    public HighSchoolStudent(String personalNo, String schoolName) {
        super(personalNo, schoolName);
        University.increaseStudentCount(this);
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
