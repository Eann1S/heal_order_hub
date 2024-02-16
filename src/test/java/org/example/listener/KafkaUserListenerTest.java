package org.example.listener;

import org.example.dto.mq_dto.RegisterDto;
import org.example.service.UserService;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.example.config.gson.GsonConfig.GSON;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class, InstancioExtension.class})
class KafkaUserListenerTest {

    @Mock
    private UserService userService;
    private KafkaUserListener kafkaUserListener;

    @BeforeEach
    void setUp() {
        kafkaUserListener = new KafkaUserListener(userService);
    }

    @ParameterizedTest
    @InstancioSource
    void shouldCreateUserFromRegisterMessage(RegisterDto registerDto) {
        kafkaUserListener.createUserFromRegisterMessage(GSON.toJson(registerDto));

        verify(userService).createUserFromRegistrationDto(registerDto);
    }
}