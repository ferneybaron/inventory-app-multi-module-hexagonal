package com.fbaron.ims.product.mongo.repository;

import com.fbaron.ims.product.mongo.entity.ProductMongoEntity;
import com.fbaron.ims.product.mongo.mapper.ProductMongoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "mongo")
@RequiredArgsConstructor
public class ProductMongoRepository {

    private final MongoTemplate mongoTemplate;
    private final ProductMongoMapper mapper;

    public ProductMongoEntity save(ProductMongoEntity entity) {
        ProductMongoEntity toSave = entity;
        if (entity.getId() == null) {
            toSave = mapper.withId(entity, UUID.randomUUID());
        }
        return mongoTemplate.save(toSave);
    }

    public List<ProductMongoEntity> findAll() {
        return mongoTemplate.findAll(ProductMongoEntity.class);
    }

    public Optional<ProductMongoEntity> findById(UUID id) {
        return Optional.ofNullable(mongoTemplate.findById(id, ProductMongoEntity.class));
    }
}
