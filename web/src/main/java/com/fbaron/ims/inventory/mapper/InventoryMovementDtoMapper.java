package com.fbaron.ims.inventory.mapper;

import com.fbaron.ims.inventory.dto.InventoryMovementDto;
import com.fbaron.ims.inventory.dto.RegisterInventoryMovementDto;
import com.fbaron.ims.inventory.model.InventoryMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMovementDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    InventoryMovement toModel(RegisterInventoryMovementDto source);

    InventoryMovementDto toDto(InventoryMovement source);

    List<InventoryMovementDto> toDto(List<InventoryMovement> source);

}
