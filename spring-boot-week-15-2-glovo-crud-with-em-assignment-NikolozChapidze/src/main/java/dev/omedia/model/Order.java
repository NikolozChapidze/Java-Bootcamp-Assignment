package dev.omedia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {
    @Id
    @Column(name = "order_id")
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "order_address")
    private String orderAddress;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "order_date")
    private LocalDate orderDate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer orderCustomer;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "courier_id", nullable = false)
    private Courier orderCourier;

    @JsonIgnore
    @OneToMany(mappedBy = "orderId")
    private List<OrderProduct> orderProducts;
}
