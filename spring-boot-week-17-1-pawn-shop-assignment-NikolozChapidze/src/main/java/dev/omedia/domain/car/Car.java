package dev.omedia.domain.car;

import dev.omedia.domain.Item;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "car")
public class Car extends Item {

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "car_model_id")
    @NotNull
    private CarModel carModel;

    @Column(name = "manufacture_year")
    @Range(min = 1900, max = 2022)
    @NotNull
    private int manufactureYear;

    @Column(name = "odometer_reading")
    @Positive
    private double odometerReading;


    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "odometer_unit")
    private OdometerUnit odometerUnit;

}
