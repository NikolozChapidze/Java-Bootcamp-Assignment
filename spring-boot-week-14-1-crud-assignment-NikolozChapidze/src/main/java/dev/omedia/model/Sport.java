package dev.omedia.model;

import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sport {
    private long id;
    @EqualsAndHashCode.Include
    private final String name;
    private final String description;
    private final int playersNum;
}
