package com.fbaron.ims.inventory.jdbc.mapper;

import com.fbaron.ims.inventory.jdbc.entity.InventoryMovementJdbcEntity;
import com.fbaron.ims.inventory.model.InventoryMovement;
import com.fbaron.ims.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class InventoryMovementJdbcMapper {

    public InventoryMovementJdbcEntity toJdbcEntity(InventoryMovement source) {
        return InventoryMovementJdbcEntity.builder()
                .id(source.getId())
                .productId(source.getProduct().getId())
                .quantity(source.getQuantity())
                .type(source.getType())
                .createdAt(source.getCreatedAt())
                .reason(source.getReason())
                .build();
    }

    public InventoryMovement toModel(InventoryMovementJdbcEntity source, Product product) {
        return InventoryMovement.builder()
                .id(source.getId())
                .product(product)
                .quantity(source.getQuantity())
                .type(source.getType())
                .createdAt(source.getCreatedAt())
                .reason(source.getReason())
                .build();
    }

    public InventoryMovementJdbcEntity withId(InventoryMovementJdbcEntity source, Long id) {
        return InventoryMovementJdbcEntity.builder()
                .id(id)
                .productId(source.getProductId())
                .quantity(source.getQuantity())
                .type(source.getType())
                .createdAt(source.getCreatedAt())
                .reason(source.getReason())
                .build();
    }
}
