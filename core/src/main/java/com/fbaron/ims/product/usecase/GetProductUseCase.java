/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims.product.usecase;

import com.fbaron.ims.product.model.Product;

import java.util.List;
import java.util.UUID;

public interface GetProductUseCase {

    Product getById(UUID id);

    List<Product> getAll();

}
