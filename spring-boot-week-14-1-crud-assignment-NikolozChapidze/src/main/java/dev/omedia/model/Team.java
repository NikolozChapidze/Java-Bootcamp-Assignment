package dev.omedia.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Team {
    private long id;
    @EqualsAndHashCode.Include
    private final String name;
    private final int sportsmanNum;
    private final LocalDate foundingDate;
    private Sport sport;
}
