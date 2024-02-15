package org.example.service;

import org.example.dto.OrderDto;
import org.example.dto.request.OrderCreationRequest;
import org.example.entity.Order;

public interface OrderService {

    OrderDto getOrderDtoById(String id);
    Order findOrderByIdInDatabase(String id);
    void createOrder(OrderCreationRequest request);
}
