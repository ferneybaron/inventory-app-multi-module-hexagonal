package com.fbaron.ims.inventory.elasticsearch.mapper;

import com.fbaron.ims.inventory.elasticsearch.entity.InventoryMovementElasticsearchEntity;
import com.fbaron.ims.inventory.model.InventoryMovement;
import com.fbaron.ims.inventory.model.MovementType;
import com.fbaron.ims.product.model.Product;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InventoryMovementElasticsearchMapper {

    public InventoryMovementElasticsearchEntity toEntity(InventoryMovement source) {
        return InventoryMovementElasticsearchEntity.builder()
                .id(source.getId() != null ? source.getId().toString() : null)
                .productId(source.getProduct().getId().toString())
                .quantity(source.getQuantity())
                .type(source.getType().name())
                .createdAt(source.getCreatedAt())
                .reason(source.getReason())
                .build();
    }

    public InventoryMovement toModel(InventoryMovementElasticsearchEntity source, Product product) {
        return InventoryMovement.builder()
                .id(source.getId() != null ? UUID.fromString(source.getId()) : null)
                .product(product)
                .quantity(source.getQuantity())
                .type(MovementType.valueOf(source.getType()))
                .createdAt(source.getCreatedAt())
                .reason(source.getReason())
                .build();
    }

    public InventoryMovementElasticsearchEntity withId(InventoryMovementElasticsearchEntity source, UUID id) {
        return InventoryMovementElasticsearchEntity.builder()
                .id(id.toString())
                .productId(source.getProductId())
                .quantity(source.getQuantity())
                .type(source.getType())
                .createdAt(source.getCreatedAt())
                .reason(source.getReason())
                .build();
    }
}
