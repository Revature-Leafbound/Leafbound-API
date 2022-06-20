package com.leafbound.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leafbound.models.Order;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, UUID> {

	@Query(value = "SELECT * FROM orders where order_date=?1", nativeQuery = true)
	public List<Order> findByDate(LocalDate orderDate);

	@Query(value = "SELECT * FROM orders where user_id=?1", nativeQuery = true)
	public List<Order> findByCustomerId(UUID customerId);

}
