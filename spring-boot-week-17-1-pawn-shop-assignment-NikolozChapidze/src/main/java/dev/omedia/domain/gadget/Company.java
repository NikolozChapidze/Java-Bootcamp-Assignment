package dev.omedia.domain.gadget;

import dev.omedia.domain.GenericEntity;
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
@Table(name = "companies")
public class Company implements Serializable, GenericEntity<Company> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company_code", length = 15, nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 15, max = 15, message = "not enough characters")
    private String companyCode;

    @Column(name = "company_name", length = 30, nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 3, message = "not enough characters")
    @Size(min = 30, message = "too much characters")
    private String companyName;

    @Override
    public void update(Company source) {
        this.companyCode = source.getCompanyCode();
        this.companyName = source.getCompanyName();
    }
}
