package com.fbaron.ims.inventory.jdbc.repository;

import com.fbaron.ims.inventory.jdbc.entity.InventoryMovementJdbcEntity;
import com.fbaron.ims.inventory.jdbc.mapper.InventoryMovementJdbcMapper;
import com.fbaron.ims.inventory.model.MovementType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
@Profile("jdbc")
@RequiredArgsConstructor
public class InventoryMovementJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final InventoryMovementJdbcMapper mapper;

    public InventoryMovementJdbcEntity save(InventoryMovementJdbcEntity inventoryMovement) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO inventory_movement (product_id, quantity, type, created_at, reason) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setLong(1, inventoryMovement.getProductId());
            ps.setInt(2, inventoryMovement.getQuantity());
            ps.setString(3, inventoryMovement.getType().name());
            ps.setObject(4, inventoryMovement.getCreatedAt());
            ps.setString(5, inventoryMovement.getReason());
            return ps;
        }, keyHolder);

        Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return mapper.withId(inventoryMovement, generatedId);
    }

    public Integer findTotalInputs(Long productId) {
        Integer total = jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(quantity), 0) FROM inventory_movement WHERE product_id = ? AND type = ?",
                Integer.class,
                productId,
                MovementType.INBOUND.name()
        );
        return total != null ? total : 0;
    }

    public Integer findTotalOutputs(Long productId) {
        Integer total = jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(quantity), 0) FROM inventory_movement WHERE product_id = ? AND type = ?",
                Integer.class,
                productId,
                MovementType.OUTBOUND.name()
        );
        return total != null ? total : 0;
    }
}
