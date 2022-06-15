package com.leafbound.services;

import java.util.List;
import java.util.UUID;

import com.leafbound.models.OrderDetailsDTO;

public interface OrderDetailsService {

    public boolean create(OrderDetailsDTO orderDetailsDTO);

    public List<OrderDetailsDTO> getByOrderId(UUID orderId);

    public boolean update(OrderDetailsDTO orderDetailsDTO);

    public boolean delete(OrderDetailsDTO orderDetailsDTO);

}
