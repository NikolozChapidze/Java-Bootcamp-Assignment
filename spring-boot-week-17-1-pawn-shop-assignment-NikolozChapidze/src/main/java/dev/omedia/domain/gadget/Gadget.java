package dev.omedia.domain.gadget;

import dev.omedia.domain.Item;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "gadget")
public class Gadget extends Item {
    @Column(name = "gadget_category")
    @Enumerated(EnumType.STRING)
    @NotNull
    private GadgetCategory gadgetCategory;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @NotNull
    @JoinColumn(name = "manufacturer_id")
    private Company manufacturer;

    @Column(name = "damage", length = 1500)
    @Lob
    private String damage;
}
