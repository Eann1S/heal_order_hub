package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.OrderDto;
import org.example.dto.request.OrderCreationRequest;
import org.example.entity.Order;
import org.example.exception.OrderNotFoundException;
import org.example.mapper.OrderMapper;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto getOrderDtoById(String id) {
        Order order = findOrderByIdInDatabase(id);
        return orderMapper.mapOrderToOrderDto(order);
    }

    @Override
    public Order findOrderByIdInDatabase(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public void createOrder(OrderCreationRequest request) {
        Order order = orderMapper.mapOrderCreationRequestToOrder(request);
        orderRepository.saveAndFlush(order);
    }
}
