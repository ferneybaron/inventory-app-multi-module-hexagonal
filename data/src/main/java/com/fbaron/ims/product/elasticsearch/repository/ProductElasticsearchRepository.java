package com.fbaron.ims.product.elasticsearch.repository;

import com.fbaron.ims.product.elasticsearch.entity.ProductElasticsearchEntity;
import com.fbaron.ims.product.elasticsearch.mapper.ProductElasticsearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "elasticsearch")
@RequiredArgsConstructor
public class ProductElasticsearchRepository {

    private final ElasticsearchOperations operations;
    private final ProductElasticsearchMapper mapper;

    public ProductElasticsearchEntity save(ProductElasticsearchEntity entity) {
        ProductElasticsearchEntity toSave = entity;
        if (entity.getId() == null) {
            toSave = mapper.withId(entity, UUID.randomUUID());
        }
        return operations.save(toSave);
    }

    public List<ProductElasticsearchEntity> findAll() {
        NativeQuery query = NativeQuery.builder().withQuery(q -> q.matchAll(m -> m)).build();
        return operations.search(query, ProductElasticsearchEntity.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }

    public Optional<ProductElasticsearchEntity> findById(UUID id) {
        return Optional.ofNullable(operations.get(id.toString(), ProductElasticsearchEntity.class));
    }
}
