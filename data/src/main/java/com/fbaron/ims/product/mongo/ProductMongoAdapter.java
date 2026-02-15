package com.fbaron.ims.product.mongo;

import com.fbaron.ims.product.model.Product;
import com.fbaron.ims.product.mongo.mapper.ProductMongoMapper;
import com.fbaron.ims.product.mongo.repository.ProductMongoRepository;
import com.fbaron.ims.product.repository.ProductCommandRepository;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "mongo")
@RequiredArgsConstructor
public class ProductMongoAdapter implements ProductQueryRepository, ProductCommandRepository {

    private final ProductMongoRepository mongoRepository;
    private final ProductMongoMapper mongoMapper;

    @Override
    public Product save(Product product) {
        var mongoEntity = mongoMapper.toMongoEntity(product);
        return mongoMapper.toModel(mongoRepository.save(mongoEntity));
    }

    @Override
    public List<Product> findAll() {
        return mongoRepository.findAll().stream().map(mongoMapper::toModel).toList();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return mongoRepository.findById(id).map(mongoMapper::toModel);
    }
}
