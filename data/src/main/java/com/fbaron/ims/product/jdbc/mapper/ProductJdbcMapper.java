package com.fbaron.ims.product.jdbc.mapper;

import com.fbaron.ims.product.jdbc.entity.ProductJdbcEntity;
import com.fbaron.ims.product.model.Product;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class ProductJdbcMapper {

    public ProductJdbcEntity toJdbcEntity(ResultSet rs) throws SQLException {
        return ProductJdbcEntity.builder()
                .id(rs.getObject("id", java.util.UUID.class))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getBigDecimal("price"))
                .category(rs.getString("category"))
                .build();
    }

    public Product toModel(ProductJdbcEntity source) {
        return Product.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .category(source.getCategory())
                .build();
    }

    public ProductJdbcEntity toJdbcEntity(Product source) {
        return ProductJdbcEntity.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .category(source.getCategory())
                .build();
    }

    public ProductJdbcEntity withId(ProductJdbcEntity source, UUID id) {
        return ProductJdbcEntity.builder()
                .id(id)
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .category(source.getCategory())
                .build();
    }
}
