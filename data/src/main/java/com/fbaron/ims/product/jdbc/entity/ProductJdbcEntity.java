package com.fbaron.ims.product.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductJdbcEntity {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
}
