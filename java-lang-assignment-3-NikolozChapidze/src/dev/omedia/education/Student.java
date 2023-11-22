package dev.omedia.education;

public class Student {
    private School school;
    private String firstName;
    private String surname;

    public Student(School school) {
        this.school = school;
    }

    public String info(){
        String result = firstName + " " + surname +" სტუდენტი სწავლობს " + school.getSchoolName() + " ";
        result += (school.isHighSchool() ? "უმაღლეს" : "საშუალო") + " სკოლაში.";
        return result;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
