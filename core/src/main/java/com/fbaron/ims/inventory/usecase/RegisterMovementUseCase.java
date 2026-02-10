package com.fbaron.ims.inventory.usecase;

import com.fbaron.ims.inventory.model.InventoryMovement;

public interface RegisterMovementUseCase {

    InventoryMovement inbound(Long productId, Integer quantity, String reason);

    InventoryMovement outbound(Long productId, Integer quantity, String reason);

}
