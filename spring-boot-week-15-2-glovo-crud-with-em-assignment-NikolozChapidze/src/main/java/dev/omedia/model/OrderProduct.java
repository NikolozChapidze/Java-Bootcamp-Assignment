package dev.omedia.model;

import lombok.*;

import javax.persistence.*;
@Entity
@Table(name="order_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderProduct {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="order_product_id")
    private Long orderItemId;

    @Column(name="op_quantity")
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "order_id", nullable = false)
    private Order orderId;

    @ManyToOne
    @JoinColumn(name = "op_product")
    private Product product;

}
