package dev.omedia.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_group",discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @NotNull
    private Person owner;

    @NotNull
    @PastOrPresent
    @Column(name = "pawn_date", nullable = false)
    private LocalDate pawnDate;

    @NotNull
    @PastOrPresent
    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    @NotNull
    private Shop shop;

    @NotNull
    @Positive
    @Column(name = "item_price",nullable = false)
    private double itemPrice;

    @NotNull
    @Positive
    @Column(name = "payed_upfront",nullable = false)
    private double payedUpfront;

    @NotNull
    @Positive
    @Column(name = "total_payed",nullable = false)
    private double totalPayed;

    @NotNull
    @Positive
    @Column(name = "monthly_payment",nullable = false)
    private double monthlyPayment;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "item_status")
    private ItemStatus itemStatus;
}
