package com.fbaron.ims.product.jdbc.repository;

import com.fbaron.ims.product.jdbc.entity.ProductJdbcEntity;
import com.fbaron.ims.product.jdbc.mapper.ProductJdbcMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Profile("jdbc")
@RequiredArgsConstructor
public class ProductJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ProductJdbcMapper mapper;

    public ProductJdbcEntity save(ProductJdbcEntity product) {
        if (product.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO products (name, description, price, category) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setBigDecimal(3, product.getPrice());
                ps.setString(4, product.getCategory());
                return ps;
            }, keyHolder);

            Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
            return mapper.withId(product, generatedId);
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

    public Optional<ProductJdbcEntity> findById(Long id) {
        List<ProductJdbcEntity> products = jdbcTemplate.query(
                "SELECT id, name, description, price, category FROM products WHERE id = ?",
                (rs, rowNum) -> mapper.toJdbcEntity(rs),
                id
        );
        return products.stream().findFirst();
    }
}
