package com.fbaron.ims.inventory.dto;

public record RegisterInventoryMovementDto(
        Long productId,
        Integer quantity,
        String reason) {
}
