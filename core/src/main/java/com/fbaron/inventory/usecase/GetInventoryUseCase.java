/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.inventory.usecase;

import com.fbaron.inventory.model.Product;

import java.util.List;

public interface GetInventoryUseCase {
    List<Product> execute();
}