/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims.product.usecase;

import com.fbaron.ims.product.model.Product;

import java.util.List;

public interface GetProductUseCase {

    Product getById(Long id);

    List<Product> getAll();

}