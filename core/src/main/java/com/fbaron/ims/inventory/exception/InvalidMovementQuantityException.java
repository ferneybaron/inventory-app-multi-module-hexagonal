package com.fbaron.ims.inventory.exception;

public class InvalidMovementQuantityException extends RuntimeException {

    public InvalidMovementQuantityException(Integer quantity) {
        super("Movement quantity must be greater than zero. Received: " + quantity);
    }
}
