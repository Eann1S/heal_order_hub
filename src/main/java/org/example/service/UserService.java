package org.example.service;

import org.example.dto.UserDto;
import org.example.entity.User;

public interface UserService {

    UserDto getUserDtoById(String id);

    User findUserByIdInDatabase(String id);
}
