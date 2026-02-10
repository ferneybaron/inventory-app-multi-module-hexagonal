package com.fbaron.ims.inventory.repository;

import com.fbaron.ims.inventory.model.InventoryMovement;

public interface InventoryMovementCommandRepository {

    InventoryMovement save(InventoryMovement inventoryMovement);

}
