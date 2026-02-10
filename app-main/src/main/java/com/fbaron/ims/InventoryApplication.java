/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fbaron.ims")
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }
}