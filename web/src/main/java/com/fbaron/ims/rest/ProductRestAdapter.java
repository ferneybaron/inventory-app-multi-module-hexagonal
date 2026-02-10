/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims.rest;

import com.fbaron.ims.dto.ProductDto;
import com.fbaron.ims.dto.RegisterProductDto;
import com.fbaron.ims.mapper.ProductDtoMapper;
import com.fbaron.ims.product.usecase.GetProductUseCase;
import com.fbaron.ims.product.usecase.RegisterProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestAdapter {

    private final GetProductUseCase getProductUseCase;
    private final RegisterProductUseCase registerProductUseCase;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    public ProductDto registerProduct(@RequestBody RegisterProductDto dto) {
        var product = productDtoMapper.toModel(dto);
        var registeredProduct = registerProductUseCase.register(product);
        return productDtoMapper.toDto(registeredProduct);
    }

    @GetMapping
    public List<ProductDto> getAll() {
        var products = getProductUseCase.getAll();
        return productDtoMapper.toDto(products);
    }

    @GetMapping("/{productId}")
    public ProductDto getById(@PathVariable("productId") Long productId) {
        var product = getProductUseCase.getById(productId);
        return productDtoMapper.toDto(product);
    }

}