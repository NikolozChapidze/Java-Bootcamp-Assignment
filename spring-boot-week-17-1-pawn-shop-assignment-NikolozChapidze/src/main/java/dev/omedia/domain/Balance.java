package dev.omedia.domain;

import dev.omedia.domain.car.CarMake;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "balances")
public class Balance implements Serializable, GenericEntity<Balance>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "amount", nullable = false)
    @NotNull
    @Positive
    private double amount;

    @Override
    public void update(Balance source) {
        this.person = source.getPerson();
        this.amount = source.getAmount();
    }
}
