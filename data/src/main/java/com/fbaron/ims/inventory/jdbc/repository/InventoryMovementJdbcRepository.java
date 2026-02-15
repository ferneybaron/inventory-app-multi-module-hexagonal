package com.fbaron.ims.inventory.jdbc.repository;

import com.fbaron.ims.inventory.jdbc.entity.InventoryMovementJdbcEntity;
import com.fbaron.ims.inventory.jdbc.mapper.InventoryMovementJdbcMapper;
import com.fbaron.ims.inventory.model.MovementType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "app.persistence.type", havingValue = "jdbc")
@RequiredArgsConstructor
public class InventoryMovementJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final InventoryMovementJdbcMapper mapper;

    public InventoryMovementJdbcEntity save(InventoryMovementJdbcEntity inventoryMovement) {
        if (inventoryMovement.getId() == null) {
            inventoryMovement = mapper.withId(inventoryMovement, UUID.randomUUID());
        }

        InventoryMovementJdbcEntity toSave = inventoryMovement;
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO inventory_movement (id, product_id, quantity, type, created_at, reason) VALUES (?, ?, ?, ?, ?, ?)"
            );
            ps.setObject(1, toSave.getId());
            ps.setObject(2, toSave.getProductId());
            ps.setInt(3, toSave.getQuantity());
            ps.setString(4, toSave.getType().name());
            ps.setObject(5, toSave.getCreatedAt());
            ps.setString(6, toSave.getReason());
            return ps;
        });

        return toSave;
    }

    public Integer findTotalInputs(UUID productId) {
        Integer total = jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(quantity), 0) FROM inventory_movement WHERE product_id = ? AND type = ?",
                Integer.class,
                productId,
                MovementType.INBOUND.name()
        );
        return total != null ? total : 0;
    }

    public Integer findTotalOutputs(UUID productId) {
        Integer total = jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(quantity), 0) FROM inventory_movement WHERE product_id = ? AND type = ?",
                Integer.class,
                productId,
                MovementType.OUTBOUND.name()
        );
        return total != null ? total : 0;
    }
}
