package dev.omedia.domain;

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
@Table(name = "Shops")
public class Shop implements Serializable, GenericEntity<Shop>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "address", nullable = false, length = 64)
    @NotNull
    @NotBlank
    private String address;

    @Override
    public void update(Shop source) {
        this.address = source.getAddress();
    }
}
