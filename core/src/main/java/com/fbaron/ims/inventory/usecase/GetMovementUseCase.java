package com.fbaron.ims.inventory.usecase;

import java.util.UUID;

public interface GetMovementUseCase {

    Integer calculateStock(UUID productId);

}
