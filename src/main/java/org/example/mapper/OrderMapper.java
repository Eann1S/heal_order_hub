package org.example.mapper;

import org.example.dto.OrderDto;
import org.example.dto.request.OrderCreateRequest;
import org.example.entity.Order;
import org.example.mapper.qualifier.owner.OwnerById;
import org.example.mapper.qualifier.owner.OwnerQualifier;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class, OwnerQualifier.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OrderMapper {

    OrderDto mapOrderToOrderDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", source = "ownerId", qualifiedBy = OwnerById.class)
    Order mapOrderCreationRequestToOrder(OrderCreateRequest request);
}
