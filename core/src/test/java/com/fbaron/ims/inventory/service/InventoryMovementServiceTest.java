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
        InvalidMovementQuantityException exception = assertThrows(
                InvalidMovementQuantityException.class,
                () -> inventoryMovementService.inbound(1L, 0, "restock")
        );

        assertEquals("Movement quantity must be greater than zero. Received: 0", exception.getMessage());
        verify(movementCommand, never()).save(any());
    }

    @Test
    void outboundThrowsWhenStockIsInsufficient() {
        Product product = Product.builder().id(1L).name("Mouse").build();
        when(productQuery.findById(1L)).thenReturn(Optional.of(product));
        when(movementQuery.findTotalInputs(1L)).thenReturn(5);
        when(movementQuery.findTotalOutputs(1L)).thenReturn(4);

        assertThrows(
                InsufficientStockException.class,
                () -> inventoryMovementService.outbound(1L, 2, "sale")
        );

        verify(movementCommand, never()).save(any());
    }

    @Test
    void outboundPersistsMovementWhenStockIsAvailable() {
        Product product = Product.builder().id(1L).name("Mouse").build();
        when(productQuery.findById(1L)).thenReturn(Optional.of(product));
        when(movementQuery.findTotalInputs(1L)).thenReturn(10);
        when(movementQuery.findTotalOutputs(1L)).thenReturn(3);
        when(movementCommand.save(any(InventoryMovement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        InventoryMovement result = inventoryMovementService.outbound(1L, 4, "sale");

        assertEquals(MovementType.OUTBOUND, result.getType());
        assertEquals(4, result.getQuantity());
        assertEquals(product, result.getProduct());
        verify(movementCommand).save(any(InventoryMovement.class));
    }

    @Test
    void calculateStockThrowsWhenProductDoesNotExist() {
        when(productQuery.findById(404L)).thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> inventoryMovementService.calculateStock(404L)
        );
    }

    @Test
    void calculateStockReturnsInputsMinusOutputs() {
        Product product = Product.builder().id(1L).name("Laptop").build();
        when(productQuery.findById(1L)).thenReturn(Optional.of(product));
        when(movementQuery.findTotalInputs(1L)).thenReturn(20);
        when(movementQuery.findTotalOutputs(1L)).thenReturn(8);

        Integer result = inventoryMovementService.calculateStock(1L);

        assertEquals(12, result);
    }
}
