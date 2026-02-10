package com.fbaron.ims.inventory.dto;

import com.fbaron.ims.inventory.model.MovementType;
import com.fbaron.ims.product.model.Product;

import java.time.LocalDateTime;

public record InventoryMovementDto(
        Long id,
        Product product,
        MovementType type,
        LocalDateTime createdAt,
        String reason) {
}
