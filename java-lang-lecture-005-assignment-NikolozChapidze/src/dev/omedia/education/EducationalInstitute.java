package dev.omedia.education;

import java.time.LocalDate;

public class EducationalInstitute {
    private LocalDate establishmentDate;
    private String name;

    public LocalDate getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
