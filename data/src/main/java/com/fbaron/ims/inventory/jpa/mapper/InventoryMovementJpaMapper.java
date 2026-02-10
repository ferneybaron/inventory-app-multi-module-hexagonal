package com.fbaron.ims.inventory.jpa.mapper;

import com.fbaron.ims.inventory.jpa.entity.InventoryMovementJpaEntity;
import com.fbaron.ims.inventory.model.InventoryMovement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMovementJpaMapper {

    InventoryMovementJpaEntity toJpaEntity(InventoryMovement source);

    InventoryMovement toModel(InventoryMovementJpaEntity source);

    List<InventoryMovement> toModel(List<InventoryMovementJpaEntity> source);

}
