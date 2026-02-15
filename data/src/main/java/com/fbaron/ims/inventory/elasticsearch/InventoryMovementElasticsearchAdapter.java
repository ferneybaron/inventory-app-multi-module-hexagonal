package com.fbaron.ims.inventory.elasticsearch;

import com.fbaron.ims.inventory.elasticsearch.mapper.InventoryMovementElasticsearchMapper;
import com.fbaron.ims.inventory.elasticsearch.repository.InventoryMovementElasticsearchRepository;
import com.fbaron.ims.inventory.model.InventoryMovement;
import com.fbaron.ims.inventory.repository.InventoryMovementCommandRepository;
import com.fbaron.ims.inventory.repository.InventoryMovementQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "elasticsearch")
@RequiredArgsConstructor
public class InventoryMovementElasticsearchAdapter implements
        InventoryMovementQueryRepository,
        InventoryMovementCommandRepository {

    private final InventoryMovementElasticsearchRepository repository;
    private final InventoryMovementElasticsearchMapper mapper;

    @Override
    public InventoryMovement save(InventoryMovement inventoryMovement) {
        var entity = mapper.toEntity(inventoryMovement);
        var savedEntity = repository.save(entity);
        return mapper.toModel(savedEntity, inventoryMovement.getProduct());
    }

    @Override
    public Integer findTotalInputs(UUID productId) {
        return repository.findTotalInputs(productId);
    }

    @Override
    public Integer findTotalOutputs(UUID productId) {
        return repository.findTotalOutputs(productId);
    }
}
