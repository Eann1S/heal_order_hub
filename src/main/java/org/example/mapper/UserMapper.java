package org.example.mapper;

import org.example.dto.UserDto;
import org.example.dto.mq_dto.RegistrationDto;
import org.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDto mapUserToUserDto(User user);

    User mapRegistrationDtoToUser(RegistrationDto registrationDto);
}
