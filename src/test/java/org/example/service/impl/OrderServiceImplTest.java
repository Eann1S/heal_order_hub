package org.example.service.impl;

import org.example.dto.OrderDto;
import org.example.dto.request.OrderCreateRequest;
import org.example.entity.Order;
import org.example.exception.OrderNotFoundException;
import org.example.mapper.OrderMapper;
import org.example.repository.OrderRepository;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.message.ErrorMessage.ORDER_NOT_FOUND;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, InstancioExtension.class})
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository, orderMapper);
    }

    @ParameterizedTest
    @InstancioSource
    void shouldReturnOrderDtoById(Order order, OrderDto orderDto) {
        when(orderRepository.findById(order.getId()))
                .thenReturn(Optional.of(order));
        when(orderMapper.mapOrderToOrderDto(order))
                .thenReturn(orderDto);

        OrderDto actualDto = orderService.getOrderDtoById(order.getId());

        assertThat(actualDto).isEqualTo(orderDto);
    }

    @ParameterizedTest
    @InstancioSource
    void shouldFindOrderByIdInDatabase(Order order) {
        when(orderRepository.findById(order.getId()))
                .thenReturn(Optional.of(order));

        Order actualOrder = orderService.findOrderByIdInDatabase(order.getId());

        assertThat(actualOrder).isEqualTo(order);
    }

    @ParameterizedTest
    @InstancioSource
    void shouldCreateOrder(OrderCreateRequest request, Order order) {
        when(orderMapper.mapOrderCreationRequestToOrder(request))
                .thenReturn(order);

        orderService.createOrder(request);

        verify(orderRepository).saveAndFlush(order);
    }

    @ParameterizedTest
    @InstancioSource
    void shouldThrowException_whenOrderByIdWasNotFound(Order order) {
        when(orderRepository.findById(order.getId()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.findOrderByIdInDatabase(order.getId()))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessage(ORDER_NOT_FOUND.formatWith(order.getId()));
    }
}