package dev.omedia.model;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Courier {
    private long id;
    @EqualsAndHashCode.Include
    private final String email;
    private final String password;
    private final String name;
    private final String lastName;
    private final String vehicle;
}
