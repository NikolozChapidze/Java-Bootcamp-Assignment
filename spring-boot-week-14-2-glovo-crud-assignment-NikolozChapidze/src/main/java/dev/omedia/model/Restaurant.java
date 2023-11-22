package dev.omedia.model;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Restaurant {
    private long id;
    private final String restaurantName;
    private final String restaurantPhone;
    private final String email;
    private Set<Long> menu;
}
