package com.fbaron.ims.inventory.jdbc.entity;

import com.fbaron.ims.inventory.model.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovementJdbcEntity {

    private Long id;
    private Long productId;
    private Integer quantity;
    private MovementType type;
    private LocalDateTime createdAt;
    private String reason;
}
