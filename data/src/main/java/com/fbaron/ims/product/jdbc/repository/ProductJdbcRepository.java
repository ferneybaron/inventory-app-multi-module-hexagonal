package com.fbaron.ims.product.jdbc.repository;

import com.fbaron.ims.product.jdbc.entity.ProductJdbcEntity;
import com.fbaron.ims.product.jdbc.mapper.ProductJdbcMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "jdbc")
@RequiredArgsConstructor
public class ProductJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ProductJdbcMapper mapper;

    public ProductJdbcEntity save(ProductJdbcEntity product) {
        if (product.getId() == null) {
            product = mapper.withId(product, UUID.randomUUID());
            ProductJdbcEntity finalProduct = product;
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO products (id, name, description, price, category) VALUES (?, ?, ?, ?, ?)"
                );
                ps.setObject(1, finalProduct.getId());
                ps.setString(2, finalProduct.getName());
                ps.setString(3, finalProduct.getDescription());
                ps.setBigDecimal(4, finalProduct.getPrice());
                ps.setString(5, finalProduct.getCategory());
                return ps;
            });
            return product;
        }

        jdbcTemplate.update(
                "UPDATE products SET name = ?, description = ?, price = ?, category = ? WHERE id = ?",
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getId()
        );
        return product;
    }

    public List<ProductJdbcEntity> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, description, price, category FROM products",
                (rs, rowNum) -> mapper.toJdbcEntity(rs)
        );
    }

    public Optional<ProductJdbcEntity> findById(UUID id) {
        List<ProductJdbcEntity> products = jdbcTemplate.query(
                "SELECT id, name, description, price, category FROM products WHERE id = ?",
                (rs, rowNum) -> mapper.toJdbcEntity(rs),
                id
        );
        return products.stream().findFirst();
    }
}
