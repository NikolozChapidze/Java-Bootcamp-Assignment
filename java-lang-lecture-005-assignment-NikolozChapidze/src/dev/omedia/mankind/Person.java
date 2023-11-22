package dev.omedia.mankind;

import java.time.LocalDate;
import java.util.Calendar;

public class Person {
    private String personalNo;
    private String name;
    private LocalDate birthday;

    public Person(String personalNo) {
        this.personalNo = personalNo;
    }

    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - birthday.getYear();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
