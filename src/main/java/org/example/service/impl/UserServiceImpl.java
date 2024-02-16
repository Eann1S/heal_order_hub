package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.dto.mq_dto.RegisterDto;
import org.example.dto.request.ContactsUpdateRequest;
import org.example.entity.User;
import org.example.exception.UserNotFoundException;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserDtoById(String id) {
        User user = findUserByIdInDatabase(id);
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public User findUserByIdInDatabase(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void createUserFromRegistrationDto(RegisterDto registerDto) {
        User user = userMapper.mapRegistrationDtoToUser(registerDto);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void updateUserContactsById(String id, ContactsUpdateRequest updateRequest) {
        User user = findUserByIdInDatabase(id);
        User updatedUser = userMapper.updateUserContacts(updateRequest, user);
        userRepository.saveAndFlush(updatedUser);
    }
}
