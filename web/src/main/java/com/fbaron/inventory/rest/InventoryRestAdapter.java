/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.inventory.rest;

import com.fbaron.inventory.dto.ProductResponseDTO;
import com.fbaron.inventory.usecase.GetInventoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class InventoryRestAdapter {

    private final GetInventoryUseCase getInventoryUseCase;

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return getInventoryUseCase.execute().stream()
                .map(product -> ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .stock(product.getStockQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}