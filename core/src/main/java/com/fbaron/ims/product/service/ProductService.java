/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims.product.service;

import com.fbaron.ims.product.exception.ProductNotFoundException;
import com.fbaron.ims.product.model.Product;
import com.fbaron.ims.product.repository.ProductCommandRepository;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import com.fbaron.ims.product.usecase.GetProductUseCase;
import com.fbaron.ims.product.usecase.RegisterProductUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductService implements RegisterProductUseCase, GetProductUseCase {

    private final ProductQueryRepository productQueryRepository;
    private final ProductCommandRepository productCommandRepository;

    @Override
    public Product register(Product product) {
        return productCommandRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productQueryRepository.findAll();
    }

    @Override
    public Product getById(UUID id) {
        return productQueryRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

}
