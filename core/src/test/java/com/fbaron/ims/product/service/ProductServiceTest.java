package com.fbaron.ims.product.service;

import com.fbaron.ims.product.exception.ProductNotFoundException;
import com.fbaron.ims.product.model.Product;
import com.fbaron.ims.product.repository.ProductCommandRepository;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductQueryRepository productQueryRepository;
    @Mock
    private ProductCommandRepository productCommandRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void registerDelegatesToCommandRepository() {
        Product product = Product.builder().name("Keyboard").build();
        Product savedProduct = Product.builder().id(1L).name("Keyboard").build();
        when(productCommandRepository.save(product)).thenReturn(savedProduct);

        Product result = productService.register(product);

        assertEquals(savedProduct, result);
        verify(productCommandRepository).save(product);
    }

    @Test
    void getByIdThrowsWhenProductIsMissing() {
        when(productQueryRepository.findById(999L)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(
                ProductNotFoundException.class,
                () -> productService.getById(999L)
        );

        assertEquals("Product not found with ID: 999", exception.getMessage());
    }
}
