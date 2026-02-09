/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.inventory.config;

import com.fbaron.inventory.repository.ProductQueryRepository;
import com.fbaron.inventory.service.InventoryService;
import com.fbaron.inventory.usecase.GetInventoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryBeanConfig {

    @Bean
    public GetInventoryUseCase getInventoryUseCase(ProductQueryRepository repository) {
        return new InventoryService(repository);
    }
}