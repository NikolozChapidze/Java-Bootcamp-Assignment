package dev.omedia.education;

import dev.omedia.mankind.HighSchoolStudent;
import dev.omedia.mankind.Student;

public class University extends EducationalInstitute{
    private static int studentCount = 0;

    public static void increaseStudentCount(Student student){
        studentCount++;
    }

    public String applyTo(HighSchoolStudent highSchoolStudent){
        if(highSchoolStudent.getGrade() >= 11){
            studentCount++;
            return "UniversityStudent";
        }
        return null;
    }

    public static int getStudentCount() {
        return studentCount;
    }

    public static void setStudentCount(int studentCount) {
        University.studentCount = studentCount;
    }
}
