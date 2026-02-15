package com.fbaron.ims.inventory.rest;

import com.fbaron.ims.inventory.dto.InventoryMovementDto;
import com.fbaron.ims.inventory.dto.RegisterInventoryMovementDto;
import com.fbaron.ims.inventory.mapper.InventoryMovementDtoMapper;
import com.fbaron.ims.inventory.usecase.GetMovementUseCase;
import com.fbaron.ims.inventory.usecase.RegisterMovementUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
public class InventoryRestAdapter {

    private final GetMovementUseCase getMovementUseCase;
    private final RegisterMovementUseCase registerMovementUseCase;
    private final InventoryMovementDtoMapper inventoryMovementDtoMapper;

    @PostMapping("/inbound")
    public ResponseEntity<InventoryMovementDto> addStock(@Valid @RequestBody RegisterInventoryMovementDto dto) {
        var registeredMovement = registerMovementUseCase.inbound(dto.productId(), dto.quantity(), dto.reason());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventoryMovementDtoMapper.toDto(registeredMovement));
    }

    @PostMapping("/outbound")
    public ResponseEntity<InventoryMovementDto> removeStock(@Valid @RequestBody RegisterInventoryMovementDto dto) {
        var registeredMovement = registerMovementUseCase.outbound(dto.productId(), dto.quantity(), dto.reason());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventoryMovementDtoMapper.toDto(registeredMovement));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Integer> getStock(@PathVariable("productId") UUID productId) {
        return ResponseEntity.ok(getMovementUseCase.calculateStock(productId));
    }

}
