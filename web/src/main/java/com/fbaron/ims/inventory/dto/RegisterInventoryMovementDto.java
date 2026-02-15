package com.fbaron.ims.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record RegisterInventoryMovementDto(
        @NotNull(message = "Product id is required")
        UUID productId,
        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be greater than zero")
        Integer quantity,
        @NotBlank(message = "Reason is required")
        String reason) {
}
