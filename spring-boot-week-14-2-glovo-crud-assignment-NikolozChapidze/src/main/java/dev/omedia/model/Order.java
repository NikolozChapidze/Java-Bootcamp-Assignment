package dev.omedia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Order {
    private long id;
    private final String orderAddress;
    private final LocalDate orderDate;
    private long customerId;
    private long courierId;
    private Map<Long, Integer> orderProducts;
}
