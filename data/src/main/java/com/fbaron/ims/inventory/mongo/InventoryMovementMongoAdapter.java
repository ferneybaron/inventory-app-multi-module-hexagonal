package com.fbaron.ims.inventory.mongo;

import com.fbaron.ims.inventory.model.InventoryMovement;
import com.fbaron.ims.inventory.mongo.mapper.InventoryMovementMongoMapper;
import com.fbaron.ims.inventory.mongo.repository.InventoryMovementMongoRepository;
import com.fbaron.ims.inventory.repository.InventoryMovementCommandRepository;
import com.fbaron.ims.inventory.repository.InventoryMovementQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "mongo")
@RequiredArgsConstructor
public class InventoryMovementMongoAdapter implements
        InventoryMovementQueryRepository,
        InventoryMovementCommandRepository {

    private final InventoryMovementMongoRepository mongoRepository;
    private final InventoryMovementMongoMapper mongoMapper;

    @Override
    public InventoryMovement save(InventoryMovement inventoryMovement) {
        var mongoEntity = mongoMapper.toMongoEntity(inventoryMovement);
        var savedMongoEntity = mongoRepository.save(mongoEntity);
        return mongoMapper.toModel(savedMongoEntity, inventoryMovement.getProduct());
    }

    @Override
    public Integer findTotalInputs(UUID productId) {
        return mongoRepository.findTotalInputs(productId);
    }

    @Override
    public Integer findTotalOutputs(UUID productId) {
        return mongoRepository.findTotalOutputs(productId);
    }
}
