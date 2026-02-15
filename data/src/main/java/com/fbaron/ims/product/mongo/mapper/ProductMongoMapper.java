package com.fbaron.ims.product.mongo.mapper;

import com.fbaron.ims.product.model.Product;
import com.fbaron.ims.product.mongo.entity.ProductMongoEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMongoMapper {

    public ProductMongoEntity toMongoEntity(Product source) {
        return ProductMongoEntity.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .category(source.getCategory())
                .build();
    }

    public Product toModel(ProductMongoEntity source) {
        return Product.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .category(source.getCategory())
                .build();
    }

    public ProductMongoEntity withId(ProductMongoEntity source, UUID id) {
        return ProductMongoEntity.builder()
                .id(id)
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .category(source.getCategory())
                .build();
    }
}
