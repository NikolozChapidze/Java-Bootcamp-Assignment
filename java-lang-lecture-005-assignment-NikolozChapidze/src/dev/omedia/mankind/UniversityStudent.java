package dev.omedia.mankind;

import dev.omedia.education.School;

public class UniversityStudent extends Student{
    private String faculty;
    private int semester;

    public UniversityStudent(String personalNo, String schoolName) {
        super(personalNo, schoolName);
        School.increaseStudentCount(this);
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
