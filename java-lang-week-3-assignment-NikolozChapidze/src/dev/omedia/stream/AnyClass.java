package dev.omedia.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class AnyClass {

    private String name;

    private String surname;

    public AnyClass(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "AnyClass{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof AnyClass
//                && this.name.equals(((AnyClass)obj).name)
                && this.surname.equals(((AnyClass)obj).surname));
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public static ArrayList<AnyClass> sortedWithFirstOccuarances(Collection<AnyClass> people){
        people.stream().filter(person -> Collections.frequency(people, person) > 1).
                findFirst().ifPresent(System.out::println);

        ArrayList<AnyClass> result = new ArrayList<>();
        people.stream().filter(person -> Collections.frequency(people, person) > 1)
                .collect(Collectors.groupingBy(AnyClass::getSurname)).values()
                .forEach(stringListEntry -> result.add(stringListEntry.get(0)));
        System.out.println("--------------------\n");
        return result;
    }
}
