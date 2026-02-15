package com.fbaron.ims.inventory.repository;

import java.util.UUID;

public interface InventoryMovementQueryRepository {

    Integer findTotalInputs(UUID productId);

    Integer findTotalOutputs(UUID productId);

}
