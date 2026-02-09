/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.inventory.repository;

import com.fbaron.inventory.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Specialized Port for read-only operations.
 */
public interface ProductQueryRepository {
    List<Product> findAll();

    Optional<Product> findById(Long id);
}