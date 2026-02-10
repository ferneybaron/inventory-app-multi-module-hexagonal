/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims.config;

import com.fbaron.ims.product.repository.ProductCommandRepository;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import com.fbaron.ims.product.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryBeanConfig {

    @Bean
    public ProductService productService(ProductQueryRepository queryRepo,
                                         ProductCommandRepository commandRepo) {
        return new ProductService(queryRepo, commandRepo);
    }

}