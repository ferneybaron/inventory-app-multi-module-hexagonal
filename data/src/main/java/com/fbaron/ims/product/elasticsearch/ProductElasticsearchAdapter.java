package com.fbaron.ims.product.elasticsearch;

import com.fbaron.ims.product.elasticsearch.mapper.ProductElasticsearchMapper;
import com.fbaron.ims.product.elasticsearch.repository.ProductElasticsearchRepository;
import com.fbaron.ims.product.model.Product;
import com.fbaron.ims.product.repository.ProductCommandRepository;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "elasticsearch")
@RequiredArgsConstructor
public class ProductElasticsearchAdapter implements ProductQueryRepository, ProductCommandRepository {

    private final ProductElasticsearchRepository repository;
    private final ProductElasticsearchMapper mapper;

    @Override
    public Product save(Product product) {
        var entity = mapper.toEntity(product);
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository.findById(id).map(mapper::toModel);
    }
}
