/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims.product.jpa.repository;

import com.fbaron.ims.product.jpa.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
}