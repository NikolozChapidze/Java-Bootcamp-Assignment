package dev.omedia.domain.jewelry;

import dev.omedia.domain.GenericEntity;
import dev.omedia.domain.car.CarMake;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gems")
public class Gem implements Serializable, GenericEntity<Gem> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gem_name", length = 32)
    @Length(min = 1, max = 32)
    @NotBlank
    @NotNull
    private String gemName;

    @Override
    public void update(Gem source) {
        this.gemName = source.getGemName();
    }
}
