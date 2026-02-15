package com.fbaron.ims.inventory.usecase;

import com.fbaron.ims.inventory.model.InventoryMovement;

import java.util.UUID;

public interface RegisterMovementUseCase {

    InventoryMovement inbound(UUID productId, Integer quantity, String reason);

    InventoryMovement outbound(UUID productId, Integer quantity, String reason);

}
