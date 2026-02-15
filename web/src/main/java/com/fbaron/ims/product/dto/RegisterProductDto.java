package com.fbaron.ims.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RegisterProductDto(
        @NotBlank(message = "Product name is required")
        String name,
        @NotNull(message = "Product price is required")
        @DecimalMin(value = "0.01", message = "Product price must be greater than zero")
        BigDecimal price,
        String description,
        @NotBlank(message = "Product category is required")
        String category) {
}
