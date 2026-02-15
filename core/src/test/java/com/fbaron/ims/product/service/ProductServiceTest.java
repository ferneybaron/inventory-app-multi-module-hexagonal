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
import java.util.UUID;

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
        Product savedProduct = Product.builder().id(UUID.randomUUID()).name("Keyboard").build();
        when(productCommandRepository.save(product)).thenReturn(savedProduct);

        Product result = productService.register(product);

        assertEquals(savedProduct, result);
        verify(productCommandRepository).save(product);
    }

    @Test
    void getByIdThrowsWhenProductIsMissing() {
        UUID productId = UUID.randomUUID();
        when(productQueryRepository.findById(productId)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(
                ProductNotFoundException.class,
                () -> productService.getById(productId)
        );

        assertEquals("Product not found with ID: " + productId, exception.getMessage());
    }
}
