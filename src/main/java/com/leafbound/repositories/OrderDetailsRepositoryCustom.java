package com.leafbound.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;

import com.leafbound.models.OrderDetails;

public interface OrderDetailsRepositoryCustom {
    @Query(value = "SELECT * FROM order_details WHERE order_id = ?1", nativeQuery = true)
    public List<OrderDetails> findByOrderId(UUID orderId);
}
