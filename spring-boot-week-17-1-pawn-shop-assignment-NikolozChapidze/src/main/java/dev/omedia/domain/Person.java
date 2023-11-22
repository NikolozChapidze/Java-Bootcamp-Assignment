package dev.omedia.domain;

import dev.omedia.domain.car.CarMake;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "people")
public class Person implements Serializable, GenericEntity<Person>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "personal_id", nullable = false, unique = true, length = 11)
    @NotNull
    @NotBlank
    @Length(min = 11, max = 11, message = "incorrect length")
    private String personalId;

    @Column(name = "fullName", nullable = false)
    @NotNull
    private String fullName;

    @Column(name = "address", length = 64)
    @NotNull
    @NotBlank
    private String address;

    @Override
    public void update(Person source) {
        this.personalId = source.getPersonalId();
        this.fullName = source.getFullName();
        this.address = source.getAddress();
    }
}
