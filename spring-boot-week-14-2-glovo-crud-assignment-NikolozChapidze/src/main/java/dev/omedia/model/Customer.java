package dev.omedia.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
    private long id;
    private final String name;
    @EqualsAndHashCode.Include
    private final String email;
    private final String password;
}