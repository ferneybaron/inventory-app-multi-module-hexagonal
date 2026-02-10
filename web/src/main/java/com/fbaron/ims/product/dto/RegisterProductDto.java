package com.fbaron.ims.product.dto;

public record RegisterProductDto(
        String name,
        Double price,
        String description,
        String category) {
}
