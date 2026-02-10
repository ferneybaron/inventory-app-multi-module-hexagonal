package com.fbaron.ims.inventory.model;

import com.fbaron.ims.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovement {

    private Long id;
    private Product product;
    private Integer quantity;
    private MovementType type;
    private LocalDateTime createdAt;
    private String reason;

}
