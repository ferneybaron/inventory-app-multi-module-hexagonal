package com.fbaron.ims.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegisterInventoryMovementDto(
        @NotNull(message = "Product id is required")
        @Positive(message = "Product id must be positive")
        Long productId,
        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be greater than zero")
        Integer quantity,
        @NotBlank(message = "Reason is required")
        String reason) {
}
