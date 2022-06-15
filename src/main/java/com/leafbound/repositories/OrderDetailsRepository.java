package com.leafbound.repositories;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leafbound.models.OrderDetails;

@Repository
@Transactional
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>, OrderDetailsRepositoryCustom {

    public List<OrderDetails> findByOrderId(UUID orderId);

}
