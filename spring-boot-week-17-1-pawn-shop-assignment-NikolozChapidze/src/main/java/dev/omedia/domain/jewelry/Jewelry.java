package dev.omedia.domain.jewelry;

import dev.omedia.domain.Item;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "jewelry")
public class Jewelry extends Item {
    @Column(name = "description", length = 1500)
    @Lob
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "metal_id")
    @NotNull
    private Metal metal;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "jewelry_gem_map",
            joinColumns = @JoinColumn(name = "jewelry_id"),
            inverseJoinColumns = @JoinColumn(name = "gem_id")
    )
    private Set<Gem> gem;
}
