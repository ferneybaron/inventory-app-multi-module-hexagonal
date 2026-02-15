package com.fbaron.ims.inventory.dto;

import com.fbaron.ims.inventory.model.MovementType;
import com.fbaron.ims.product.model.Product;

import java.time.LocalDateTime;
import java.util.UUID;

public record InventoryMovementDto(
        UUID id,
        Product product,
        MovementType type,
        LocalDateTime createdAt,
        String reason) {
}
