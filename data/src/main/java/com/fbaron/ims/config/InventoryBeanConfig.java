/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims.config;

import com.fbaron.ims.inventory.repository.InventoryMovementCommandRepository;
import com.fbaron.ims.inventory.repository.InventoryMovementQueryRepository;
import com.fbaron.ims.inventory.service.InventoryMovementService;
import com.fbaron.ims.product.repository.ProductCommandRepository;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import com.fbaron.ims.product.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryBeanConfig {

    @Bean
    public ProductService productService(ProductQueryRepository thisQueryRepository,
                                         ProductCommandRepository thisCommandRepository) {
        return new ProductService(thisQueryRepository, thisCommandRepository);
    }

    @Bean
    public InventoryMovementService inventoryMovementService(ProductQueryRepository productQueryRepository,
                                                             InventoryMovementCommandRepository thisCommandRepository,
                                                             InventoryMovementQueryRepository thisQueryRepository) {
        return new InventoryMovementService(productQueryRepository, thisCommandRepository, thisQueryRepository);
    }

}