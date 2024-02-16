package org.example.mapper;

import org.example.dto.UserDto;
import org.example.dto.mq_dto.RegisterDto;
import org.example.dto.request.ContactsUpdateRequest;
import org.example.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDto mapUserToUserDto(User user);

    User mapRegistrationDtoToUser(RegisterDto registerDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "username", ignore = true)
    User updateUserContacts(ContactsUpdateRequest updateRequest, @MappingTarget User userToUpdate);
}
