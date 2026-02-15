/**
 * Project: Inventory Modular Hexagonal
 * Developer: Ferney Estupiñán Barón
 * Email: ferney.estupinanb@gmail.com
 * GitHub: https://github.com/ferneybaron
 */
package com.fbaron.ims.product.jpa;

import com.fbaron.ims.product.jpa.mapper.ProductJpaMapper;
import com.fbaron.ims.product.jpa.repository.ProductJpaRepository;
import com.fbaron.ims.product.model.Product;
import com.fbaron.ims.product.repository.ProductCommandRepository;
import com.fbaron.ims.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component // This allows Spring to find it during scanning
@Profile("!jdbc")
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductQueryRepository, ProductCommandRepository {

    private final ProductJpaRepository jpaRepository;
    private final ProductJpaMapper productJpaMapper;

    @Override
    public Product save(Product product) {
        var jpaEntity = productJpaMapper.toJpaEntity(product);
        return productJpaMapper.toModel(jpaRepository.save(jpaEntity));
    }

    @Override
    public List<Product> findAll() {
        var jpaEntities = jpaRepository.findAll();
        return productJpaMapper.toModel(jpaEntities);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id).map(productJpaMapper::toModel);
    }

}
