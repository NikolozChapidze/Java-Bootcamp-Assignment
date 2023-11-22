package dev.omedia.domain.car;

import dev.omedia.domain.GenericEntity;
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
@Table(name = "car_makes")
public class CarMake implements Serializable, GenericEntity<CarMake> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "make_name")
    @Length(min = 1, max = 15)
    private String makeName;

    @Override
    public void update(CarMake source) {
        this.makeName = source.getMakeName();
    }

}
