package dev.omedia.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sportsman {
    private long id;
    @EqualsAndHashCode.Include
    private final String personalId;
    private final String fullName;
    private final LocalDate birthday;
    private Team team;
}
