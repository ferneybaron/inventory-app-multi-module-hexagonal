package com.fbaron.ims.inventory.jpa.repository;

import com.fbaron.ims.inventory.jpa.entity.InventoryMovementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryMovementJpaRepository extends JpaRepository<InventoryMovementJpaEntity, Long> {

    @Query("""
            SELECT COALESCE(SUM(i.quantity), 0)
            FROM InventoryMovementJpaEntity i
            WHERE i.product.id = :productId AND i.type = 'INBOUND'
            """)
    Integer findTotalInputs(@Param("productId") Long productId);

    @Query("""
            SELECT COALESCE(SUM(i.quantity), 0)
            FROM InventoryMovementJpaEntity i
            WHERE i.product.id = :productId AND i.type = 'OUTBOUND'
            """)
    Integer findTotalOutPuts(@Param("productId") Long productId);

}
