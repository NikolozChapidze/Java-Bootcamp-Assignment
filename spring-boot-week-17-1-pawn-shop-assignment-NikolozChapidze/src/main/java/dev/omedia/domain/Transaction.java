package dev.omedia.domain;

import dev.omedia.domain.car.CarMake;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction implements Serializable, GenericEntity<Transaction>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount", nullable = false, updatable = false)
    @Positive
    private double amount;

    @NotNull
    @PastOrPresent
    @Column(name = "transaction_date", nullable = false)
    @CreationTimestamp
    private LocalDate transactionDate;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Override
    public void update(Transaction source) {
        this.transactionDate = source.getTransactionDate();
        this.item = source.getItem();
    }
}
