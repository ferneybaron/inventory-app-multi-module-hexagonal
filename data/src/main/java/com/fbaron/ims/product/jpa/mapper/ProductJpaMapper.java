package com.fbaron.ims.product.jpa.mapper;

import com.fbaron.ims.product.jpa.entity.ProductJpaEntity;
import com.fbaron.ims.product.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductJpaMapper {

    ProductJpaEntity toJpaEntity(Product source);

    Product toModel(ProductJpaEntity source);

    List<Product> toModel(List<ProductJpaEntity> source);

}
