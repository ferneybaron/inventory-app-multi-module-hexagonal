package com.fbaron.ims.inventory.jdbc.entity;

import com.fbaron.ims.inventory.model.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovementJdbcEntity {

    private UUID id;
    private UUID productId;
    private Integer quantity;
    private MovementType type;
    private LocalDateTime createdAt;
    private String reason;
}
