/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.inventory.jpa;

import com.fbaron.inventory.jpa.repository.ProductJpaRepository;
import com.fbaron.inventory.model.Product;
import com.fbaron.inventory.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component // This allows Spring to find it during scanning
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductQueryRepository {

    private final ProductJpaRepository jpaRepository;

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    // Mapper: Entity -> Domain
    private Product toDomain(com.fbaron.inventory.jpa.entity.ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .stockQuantity(entity.getStockQuantity())
                .build();
    }
}