package com.fbaron.ims.product.jdbc;

import com.fbaron.ims.product.jdbc.mapper.ProductJdbcMapper;
import com.fbaron.ims.product.model.Product;
import com.fbaron.ims.product.jdbc.repository.ProductJdbcRepository;
import com.fbaron.ims.product.repository.ProductCommandRepository;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "jdbc")
@RequiredArgsConstructor
public class ProductJdbcAdapter implements ProductQueryRepository, ProductCommandRepository {

    private final ProductJdbcRepository jdbcRepository;
    private final ProductJdbcMapper jdbcMapper;

    @Override
    public Product save(Product product) {
        var jdbcEntity = jdbcMapper.toJdbcEntity(product);
        return jdbcMapper.toModel(jdbcRepository.save(jdbcEntity));
    }

    @Override
    public List<Product> findAll() {
        var jdbcEntities = jdbcRepository.findAll();
        return jdbcEntities.stream().map(jdbcMapper::toModel).toList();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jdbcRepository.findById(id).map(jdbcMapper::toModel);
    }
}
