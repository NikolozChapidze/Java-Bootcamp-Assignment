package dev.omedia.domain.car;


import dev.omedia.domain.GenericEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carModels")
public class CarModel implements Serializable, GenericEntity<CarModel> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_make_id", nullable = false)
    @NotNull
    private CarMake carMake;

    @NotNull
    @NotBlank
    @Column(name = "model_name")
    @Length(min = 1, max = 15)
    private String modelName;

    @Override
    public void update(CarModel source) {
        this.carMake = source.getCarMake();
        this.modelName = source.getModelName();
    }
}
