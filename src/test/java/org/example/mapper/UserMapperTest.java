package org.example.mapper;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class, InstancioExtension.class})
class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapperImpl();
    }

    @ParameterizedTest
    @InstancioSource
    void mapUserToUserDto(User user) {
        UserDto userDto = userMapper.mapUserToUserDto(user);

        assertThat(userDto)
                .extracting(UserDto::id, UserDto::email, UserDto::username, UserDto::contactsMap)
                .containsExactly(user.getId(), user.getEmail(), user.getUsername(), user.getContactsMap());
    }
}