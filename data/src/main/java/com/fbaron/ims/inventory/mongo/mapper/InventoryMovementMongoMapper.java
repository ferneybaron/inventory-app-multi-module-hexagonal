package com.fbaron.ims.inventory.mongo.mapper;

import com.fbaron.ims.inventory.model.InventoryMovement;
import com.fbaron.ims.inventory.mongo.entity.InventoryMovementMongoEntity;
import com.fbaron.ims.product.model.Product;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InventoryMovementMongoMapper {

    public InventoryMovementMongoEntity toMongoEntity(InventoryMovement source) {
        return InventoryMovementMongoEntity.builder()
                .id(source.getId())
                .productId(source.getProduct().getId())
                .quantity(source.getQuantity())
                .type(source.getType())
                .createdAt(source.getCreatedAt())
                .reason(source.getReason())
                .build();
    }

    public InventoryMovement toModel(InventoryMovementMongoEntity source, Product product) {
        return InventoryMovement.builder()
                .id(source.getId())
                .product(product)
                .quantity(source.getQuantity())
                .type(source.getType())
                .createdAt(source.getCreatedAt())
                .reason(source.getReason())
                .build();
    }

    public InventoryMovementMongoEntity withId(InventoryMovementMongoEntity source, UUID id) {
        return InventoryMovementMongoEntity.builder()
                .id(id)
                .productId(source.getProductId())
                .quantity(source.getQuantity())
                .type(source.getType())
                .createdAt(source.getCreatedAt())
                .reason(source.getReason())
                .build();
    }
}
