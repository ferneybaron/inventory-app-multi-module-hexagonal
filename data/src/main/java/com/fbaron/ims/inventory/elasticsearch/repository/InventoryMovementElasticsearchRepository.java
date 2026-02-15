package com.fbaron.ims.inventory.elasticsearch.repository;

import com.fbaron.ims.inventory.elasticsearch.entity.InventoryMovementElasticsearchEntity;
import com.fbaron.ims.inventory.elasticsearch.mapper.InventoryMovementElasticsearchMapper;
import com.fbaron.ims.inventory.model.MovementType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "elasticsearch")
@RequiredArgsConstructor
public class InventoryMovementElasticsearchRepository {

    private final ElasticsearchOperations operations;
    private final InventoryMovementElasticsearchMapper mapper;

    public InventoryMovementElasticsearchEntity save(InventoryMovementElasticsearchEntity entity) {
        InventoryMovementElasticsearchEntity toSave = entity;
        if (entity.getId() == null) {
            toSave = mapper.withId(entity, UUID.randomUUID());
        }
        return operations.save(toSave);
    }

    public Integer findTotalInputs(UUID productId) {
        return sumByProductIdAndType(productId, MovementType.INBOUND);
    }

    public Integer findTotalOutputs(UUID productId) {
        return sumByProductIdAndType(productId, MovementType.OUTBOUND);
    }

    private Integer sumByProductIdAndType(UUID productId, MovementType type) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q.bool(b -> b
                        .must(m -> m.term(t -> t.field("productId").value(productId.toString())))
                        .must(m -> m.term(t -> t.field("type").value(type.name())))))
                .build();

        return operations.search(query, InventoryMovementElasticsearchEntity.class)
                .stream()
                .map(SearchHit::getContent)
                .map(InventoryMovementElasticsearchEntity::getQuantity)
                .reduce(0, Integer::sum);
    }
}
