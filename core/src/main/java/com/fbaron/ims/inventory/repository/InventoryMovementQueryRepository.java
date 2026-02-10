package com.fbaron.ims.inventory.repository;

public interface InventoryMovementQueryRepository {

    Integer findTotalInputs(Long productId);

    Integer findTotalOutputs(Long productId);

}
