package com.fbaron.ims.exception;

import com.fbaron.ims.inventory.exception.InsufficientStockException;
import com.fbaron.ims.inventory.exception.InvalidMovementQuantityException;
import com.fbaron.ims.product.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFoundException(ProductNotFoundException ex) {
        return buildProblemDetail(HttpStatus.NOT_FOUND, "Product Not Found", ex.getMessage());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ProblemDetail handleInsufficientStockException(InsufficientStockException ex) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, "Inventory Error", ex.getMessage());
    }

    @ExceptionHandler(InvalidMovementQuantityException.class)
    public ProblemDetail handleInvalidMovementQuantityException(InvalidMovementQuantityException ex) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, "Validation Error", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return buildProblemDetail(HttpStatus.BAD_REQUEST, "Validation Error", message);
    }

    private ProblemDetail buildProblemDetail(HttpStatus status, String title, String detail) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        problem.setProperty("timestamp", LocalDateTime.now());
        return problem;
    }

}
