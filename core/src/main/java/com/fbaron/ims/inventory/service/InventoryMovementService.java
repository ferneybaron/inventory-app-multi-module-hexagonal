package com.fbaron.ims.inventory.service;

import com.fbaron.ims.inventory.exception.InsufficientStockException;
import com.fbaron.ims.inventory.exception.InvalidMovementQuantityException;
import com.fbaron.ims.inventory.model.InventoryMovement;
import com.fbaron.ims.inventory.model.MovementType;
import com.fbaron.ims.inventory.repository.InventoryMovementCommandRepository;
import com.fbaron.ims.inventory.repository.InventoryMovementQueryRepository;
import com.fbaron.ims.inventory.usecase.GetMovementUseCase;
import com.fbaron.ims.inventory.usecase.RegisterMovementUseCase;
import com.fbaron.ims.product.model.Product;
import com.fbaron.ims.product.exception.ProductNotFoundException;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class InventoryMovementService implements RegisterMovementUseCase, GetMovementUseCase {

    private final ProductQueryRepository productQuery;
    private final InventoryMovementCommandRepository movementCommand;
    private final InventoryMovementQueryRepository movementQuery;

    @Override
    public InventoryMovement inbound(Long productId, Integer quantity, String reason) {
        validateQuantity(quantity);

        Product product = productQuery.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        InventoryMovement movement = InventoryMovement.builder()
                .product(product)
                .quantity(quantity)
                .type(MovementType.INBOUND)
                .reason(reason)
                .createdAt(LocalDateTime.now())
                .build();

        return movementCommand.save(movement);
    }


    @Override
    public InventoryMovement outbound(Long productId, Integer quantity, String reason) {
        validateQuantity(quantity);

        Product product = productQuery.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Integer currentBalance = calculateStock(productId);

        if (currentBalance < quantity) {
            throw new InsufficientStockException("Cannot exit " + quantity + " units. Current balance is only " + currentBalance);
        }

        InventoryMovement movement = InventoryMovement.builder()
                .product(product)
                .quantity(quantity)
                .type(MovementType.OUTBOUND)
                .reason(reason)
                .createdAt(LocalDateTime.now())
                .build();

        return movementCommand.save(movement);
    }

    public Integer calculateStock(Long productId) {
        productQuery.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Integer inputs = movementQuery.findTotalInputs(productId);
        Integer outputs = movementQuery.findTotalOutputs(productId);

        return inputs - outputs;
    }

    private void validateQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new InvalidMovementQuantityException(quantity);
        }
    }

}
