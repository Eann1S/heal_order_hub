package org.example.mapper;

import org.example.dto.OrderDto;
import org.example.dto.UserDto;
import org.example.dto.request.OrderCreationRequest;
import org.example.entity.Order;
import org.example.entity.User;
import org.example.mapper.qualifier.owner.OwnerQualifier;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, InstancioExtension.class})
class OrderMapperTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private OwnerQualifier ownerQualifier;
    private OrderMapperImpl orderMapper;

    @BeforeEach
    void setUp() {
        orderMapper = new OrderMapperImpl(userMapper, ownerQualifier);
    }

    @ParameterizedTest
    @InstancioSource
    void shouldMapOrderToOrderDto(Order order, UserDto userDto) {
        when(userMapper.mapUserToUserDto(order.getOwner()))
                .thenReturn(userDto);

        OrderDto orderDto = orderMapper.mapOrderToOrderDto(order);

        assertThat(orderDto)
                .extracting(OrderDto::id, OrderDto::owner, OrderDto::text, OrderDto::serviceCost)
                .containsExactly(order.getId(), userDto, order.getText(), order.getServiceCost());
    }

    @ParameterizedTest
    @InstancioSource
    void shouldMapOrderCreationRequestToOrder(OrderCreationRequest request, User owner) {
        when(ownerQualifier.findOwnerById(request.ownerId()))
                .thenReturn(owner);

        Order order = orderMapper.mapOrderCreationRequestToOrder(request);

        assertThat(order)
                .extracting(Order::getOwner, Order::getText, Order::getServiceCost)
                .containsExactly(owner, request.text(), request.serviceCost());
    }
}