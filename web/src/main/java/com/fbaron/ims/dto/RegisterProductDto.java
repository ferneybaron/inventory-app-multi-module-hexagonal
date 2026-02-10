package com.fbaron.ims.dto;

public record RegisterProductDto(
        String name,
        Double price,
        String description,
        String category) {
}
