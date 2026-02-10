/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims.product.repository;

import com.fbaron.ims.product.model.Product;

/**
 * Specialized Port for creating, updating, and deleting data operations.
 */
public interface ProductCommandRepository {

    Product save(Product product);

}