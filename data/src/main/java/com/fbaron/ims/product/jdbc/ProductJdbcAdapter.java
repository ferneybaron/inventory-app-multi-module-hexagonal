package com.fbaron.ims.product.jdbc;

import com.fbaron.ims.product.jdbc.mapper.ProductJdbcMapper;
import com.fbaron.ims.product.model.Product;
import com.fbaron.ims.product.jdbc.repository.ProductJdbcRepository;
import com.fbaron.ims.product.repository.ProductCommandRepository;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("jdbc")
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
    public Optional<Product> findById(Long id) {
        return jdbcRepository.findById(id).map(jdbcMapper::toModel);
    }
}
