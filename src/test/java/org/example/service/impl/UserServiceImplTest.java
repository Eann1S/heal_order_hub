package org.example.service.impl;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.UserNotFoundException;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
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
import static org.example.message.ErrorMessage.USER_NOT_FOUND;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, InstancioExtension.class})
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @ParameterizedTest
    @InstancioSource
    void shouldReturnUserDtoById(User user, UserDto userDto) {
        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
        when(userMapper.mapUserToUserDto(user)).
                thenReturn(userDto);

        UserDto actualDto = userService.getUserDtoById(user.getId());

        assertThat(userDto).isEqualTo(actualDto);
    }

    @ParameterizedTest
    @InstancioSource
    void shouldFindUserInDatabaseById(User user) {
        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        User actualUser = userService.findUserByIdInDatabase(user.getId());

        assertThat(actualUser).isEqualTo(user);
    }

    @ParameterizedTest
    @InstancioSource
    void shouldThrowException_whenUserByIdWasNotFound(User user) {
        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findUserByIdInDatabase(user.getId()))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(USER_NOT_FOUND.formatWith(user.getId()));
    }
}