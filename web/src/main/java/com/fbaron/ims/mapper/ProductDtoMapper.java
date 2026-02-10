package com.fbaron.ims.mapper;

import com.fbaron.ims.dto.ProductDto;
import com.fbaron.ims.dto.RegisterProductDto;
import com.fbaron.ims.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {

    @Mapping(target = "id", ignore = true)
    Product toModel(RegisterProductDto source);

    ProductDto toDto(Product source);

    List<ProductDto> toDto(List<Product> source);

}
