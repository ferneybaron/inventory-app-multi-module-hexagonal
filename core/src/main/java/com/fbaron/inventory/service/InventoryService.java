/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.inventory.service;

import com.fbaron.inventory.model.Product;
import com.fbaron.inventory.repository.ProductQueryRepository;
import com.fbaron.inventory.usecase.GetInventoryUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class InventoryService implements GetInventoryUseCase {

    private final ProductQueryRepository productQueryRepository;

    @Override
    public List<Product> execute() {
        return productQueryRepository.findAll();
    }
}