package com.fbaron.ims.inventory.jpa;

import com.fbaron.ims.inventory.jpa.mapper.InventoryMovementJpaMapper;
import com.fbaron.ims.inventory.jpa.repository.InventoryMovementJpaRepository;
import com.fbaron.ims.inventory.model.InventoryMovement;
import com.fbaron.ims.inventory.repository.InventoryMovementCommandRepository;
import com.fbaron.ims.inventory.repository.InventoryMovementQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "jpa", matchIfMissing = true)
@RequiredArgsConstructor
public class InventoryMovementJpaAdapter implements
        InventoryMovementQueryRepository,
        InventoryMovementCommandRepository {

    private final InventoryMovementJpaRepository jpaRepository;
    private final InventoryMovementJpaMapper jpaMapper;

    @Override
    public InventoryMovement save(InventoryMovement inventoryMovement) {
        var jpaEntity = jpaMapper.toJpaEntity(inventoryMovement);
        return jpaMapper.toModel(jpaRepository.save(jpaEntity));
    }

    @Override
    public Integer findTotalInputs(UUID productId) {
        return jpaRepository.findTotalInputs(productId);
    }

    @Override
    public Integer findTotalOutputs(UUID productId) {
        return jpaRepository.findTotalOutPuts(productId);
    }

}
