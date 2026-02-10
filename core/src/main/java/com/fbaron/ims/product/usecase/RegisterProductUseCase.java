/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims.product.usecase;

import com.fbaron.ims.product.model.Product;

public interface RegisterProductUseCase {

    Product register(Product product);

}