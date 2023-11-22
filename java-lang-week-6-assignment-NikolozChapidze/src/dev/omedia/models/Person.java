package dev.omedia.models;

import dev.omedia.enums.AgeRange;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private final String p_id;
    private final String name;
    private final LocalDate b_date;

    private final AgeRange ageRange;

    public Person(String p_id, String name, LocalDate b_date) {
        this.p_id = p_id;
        this.name = name;
        this.b_date = b_date;
        ageRange = AgeRange.getAgeRange(Period.between(b_date, LocalDate.now()).getYears());
    }

    public String getP_id() {
        return p_id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getB_date() {
        return b_date;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }
}
