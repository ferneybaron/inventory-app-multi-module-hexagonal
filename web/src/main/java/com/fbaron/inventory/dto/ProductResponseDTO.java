/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.inventory.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
}