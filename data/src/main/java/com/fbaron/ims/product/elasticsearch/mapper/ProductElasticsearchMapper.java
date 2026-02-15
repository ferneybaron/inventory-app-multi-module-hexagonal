package com.fbaron.ims.product.elasticsearch.mapper;

import com.fbaron.ims.product.elasticsearch.entity.ProductElasticsearchEntity;
import com.fbaron.ims.product.model.Product;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductElasticsearchMapper {

    public ProductElasticsearchEntity toEntity(Product source) {
        return ProductElasticsearchEntity.builder()
                .id(source.getId() != null ? source.getId().toString() : null)
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .category(source.getCategory())
                .build();
    }

    public Product toModel(ProductElasticsearchEntity source) {
        return Product.builder()
                .id(source.getId() != null ? UUID.fromString(source.getId()) : null)
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .category(source.getCategory())
                .build();
    }

    public ProductElasticsearchEntity withId(ProductElasticsearchEntity source, UUID id) {
        return ProductElasticsearchEntity.builder()
                .id(id.toString())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .category(source.getCategory())
                .build();
    }
}
