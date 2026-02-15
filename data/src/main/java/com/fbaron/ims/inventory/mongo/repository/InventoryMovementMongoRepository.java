package com.fbaron.ims.inventory.mongo.repository;

import com.fbaron.ims.inventory.model.MovementType;
import com.fbaron.ims.inventory.mongo.entity.InventoryMovementMongoEntity;
import com.fbaron.ims.inventory.mongo.mapper.InventoryMovementMongoMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "mongo")
@RequiredArgsConstructor
public class InventoryMovementMongoRepository {

    private final MongoTemplate mongoTemplate;
    private final InventoryMovementMongoMapper mapper;

    public InventoryMovementMongoEntity save(InventoryMovementMongoEntity entity) {
        InventoryMovementMongoEntity toSave = entity;
        if (entity.getId() == null) {
            toSave = mapper.withId(entity, UUID.randomUUID());
        }
        return mongoTemplate.save(toSave);
    }

    public Integer findTotalInputs(UUID productId) {
        return sumByType(productId, MovementType.INBOUND);
    }

    public Integer findTotalOutputs(UUID productId) {
        return sumByType(productId, MovementType.OUTBOUND);
    }

    private Integer sumByType(UUID productId, MovementType type) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(org.springframework.data.mongodb.core.query.Criteria.where("productId").is(productId)
                        .and("type").is(type)),
                Aggregation.group().sum("quantity").as("total")
        );
        SumResult result = mongoTemplate.aggregate(
                aggregation,
                InventoryMovementMongoEntity.class,
                SumResult.class
        ).getUniqueMappedResult();

        return result == null || result.getTotal() == null ? 0 : result.getTotal();
    }

    @Getter
    @Setter
    static class SumResult {
        private Integer total;
    }
}
