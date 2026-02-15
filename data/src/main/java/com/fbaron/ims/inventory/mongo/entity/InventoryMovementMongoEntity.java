package com.fbaron.ims.inventory.mongo.entity;

import com.fbaron.ims.inventory.model.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "inventory_movement")
public class InventoryMovementMongoEntity {

    @Id
    private UUID id;
    private UUID productId;
    private Integer quantity;
    private MovementType type;
    private LocalDateTime createdAt;
    private String reason;
}
