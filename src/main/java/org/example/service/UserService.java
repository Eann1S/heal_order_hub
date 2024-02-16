package org.example.service;

import org.example.dto.UserDto;
import org.example.dto.mq_dto.RegisterDto;
import org.example.dto.request.ContactsUpdateRequest;
import org.example.entity.User;

public interface UserService {

    UserDto getUserDtoById(String id);

    User findUserByIdInDatabase(String id);

    void createUserFromRegistrationDto(RegisterDto registerDto);

    void updateUserContactsById(String id, ContactsUpdateRequest updateRequest);
}
