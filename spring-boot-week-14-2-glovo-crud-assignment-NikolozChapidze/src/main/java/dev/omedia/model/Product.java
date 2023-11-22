package dev.omedia.model;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product{
	private long id;
	private final String productName;
	private final String productDescription;
	private final Double productPrice;
	private long restaurantId;
}
