package com.fbaron.ims.inventory.jdbc;

import com.fbaron.ims.inventory.jdbc.mapper.InventoryMovementJdbcMapper;
import com.fbaron.ims.inventory.model.InventoryMovement;
import com.fbaron.ims.inventory.jdbc.repository.InventoryMovementJdbcRepository;
import com.fbaron.ims.inventory.repository.InventoryMovementCommandRepository;
import com.fbaron.ims.inventory.repository.InventoryMovementQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("jdbc")
@RequiredArgsConstructor
public class InventoryMovementJdbcAdapter implements
        InventoryMovementQueryRepository,
        InventoryMovementCommandRepository {

    private final InventoryMovementJdbcRepository jdbcRepository;
    private final InventoryMovementJdbcMapper jdbcMapper;

    @Override
    public InventoryMovement save(InventoryMovement inventoryMovement) {
        var jdbcEntity = jdbcMapper.toJdbcEntity(inventoryMovement);
        var savedJdbcEntity = jdbcRepository.save(jdbcEntity);
        return jdbcMapper.toModel(savedJdbcEntity, inventoryMovement.getProduct());
    }

    @Override
    public Integer findTotalInputs(Long productId) {
        return jdbcRepository.findTotalInputs(productId);
    }

    @Override
    public Integer findTotalOutputs(Long productId) {
        return jdbcRepository.findTotalOutputs(productId);
    }
}
