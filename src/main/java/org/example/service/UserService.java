package org.example.service;

import org.example.dto.UserDto;
import org.example.dto.mq_dto.RegistrationDto;
import org.example.entity.User;

public interface UserService {

    UserDto getUserDtoById(String id);

    User findUserByIdInDatabase(String id);

    void createUserFromRegistrationDto(RegistrationDto registrationDto);
}
