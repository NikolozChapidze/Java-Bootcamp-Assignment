package dev.omedia.education;

import dev.omedia.mankind.Student;

public class School extends EducationalInstitute{
    private static int studentCount = 0;

    public static void increaseStudentCount(Student student){
        studentCount++;
    }

    public static int getStudentCount() {
        return studentCount;
    }

    public static void setStudentCount(int studentCount) {
        School.studentCount = studentCount;
    }
}
