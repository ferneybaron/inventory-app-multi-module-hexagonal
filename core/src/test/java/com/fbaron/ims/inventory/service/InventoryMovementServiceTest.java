package com.fbaron.ims.inventory.service;

import com.fbaron.ims.inventory.exception.InsufficientStockException;
import com.fbaron.ims.inventory.exception.InvalidMovementQuantityException;
import com.fbaron.ims.inventory.model.InventoryMovement;
import com.fbaron.ims.inventory.model.MovementType;
import com.fbaron.ims.inventory.repository.InventoryMovementCommandRepository;
import com.fbaron.ims.inventory.repository.InventoryMovementQueryRepository;
import com.fbaron.ims.product.exception.ProductNotFoundException;
import com.fbaron.ims.product.model.Product;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryMovementServiceTest {

    @Mock
    private ProductQueryRepository productQuery;
    @Mock
    private InventoryMovementCommandRepository movementCommand;
    @Mock
    private InventoryMovementQueryRepository movementQuery;
    @InjectMocks
    private InventoryMovementService inventoryMovementService;

    @Test
    void inboundThrowsWhenQuantityIsInvalid() {
        UUID productId = UUID.randomUUID();
        InvalidMovementQuantityException exception = assertThrows(
                InvalidMovementQuantityException.class,
                () -> inventoryMovementService.inbound(productId, 0, "restock")
        );

        assertEquals("Movement quantity must be greater than zero. Received: 0", exception.getMessage());
        verify(movementCommand, never()).save(any());
    }

    @Test
    void outboundThrowsWhenStockIsInsufficient() {
        UUID productId = UUID.randomUUID();
        Product product = Product.builder().id(productId).name("Mouse").build();
        when(productQuery.findById(productId)).thenReturn(Optional.of(product));
        when(movementQuery.findTotalInputs(productId)).thenReturn(5);
        when(movementQuery.findTotalOutputs(productId)).thenReturn(4);

        assertThrows(
                InsufficientStockException.class,
                () -> inventoryMovementService.outbound(productId, 2, "sale")
        );

        verify(movementCommand, never()).save(any());
    }

    @Test
    void outboundPersistsMovementWhenStockIsAvailable() {
        UUID productId = UUID.randomUUID();
        Product product = Product.builder().id(productId).name("Mouse").build();
        when(productQuery.findById(productId)).thenReturn(Optional.of(product));
        when(movementQuery.findTotalInputs(productId)).thenReturn(10);
        when(movementQuery.findTotalOutputs(productId)).thenReturn(3);
        when(movementCommand.save(any(InventoryMovement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        InventoryMovement result = inventoryMovementService.outbound(productId, 4, "sale");

        assertEquals(MovementType.OUTBOUND, result.getType());
        assertEquals(4, result.getQuantity());
        assertEquals(product, result.getProduct());
        verify(movementCommand).save(any(InventoryMovement.class));
    }

    @Test
    void calculateStockThrowsWhenProductDoesNotExist() {
        UUID productId = UUID.randomUUID();
        when(productQuery.findById(productId)).thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> inventoryMovementService.calculateStock(productId)
        );
    }

    @Test
    void calculateStockReturnsInputsMinusOutputs() {
        UUID productId = UUID.randomUUID();
        Product product = Product.builder().id(productId).name("Laptop").build();
        when(productQuery.findById(productId)).thenReturn(Optional.of(product));
        when(movementQuery.findTotalInputs(productId)).thenReturn(20);
        when(movementQuery.findTotalOutputs(productId)).thenReturn(8);

        Integer result = inventoryMovementService.calculateStock(productId);

        assertEquals(12, result);
    }
}
