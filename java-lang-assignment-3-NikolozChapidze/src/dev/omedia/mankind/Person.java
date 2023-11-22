package dev.omedia.mankind;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.UUID;

public class Person {
    private String name;
    private String surname;
    private UUID id;
    private LocalDate birthDate;
    private String gender;
    private String country;

    public Person(String name, String surname, LocalDate birthDate, String gender, String country) {
        this.name = name;
        this.surname = surname;
        this.id = UUID.randomUUID();
        this.birthDate = birthDate;
        this.gender = gender;
        this.country = country;
    }

    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - birthDate.getYear();
    }

    public int getAge(LocalDate date){
        return Period.between(date, birthDate).getYears();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
