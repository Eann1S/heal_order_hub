package org.example.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.mq_dto.RegisterDto;
import org.example.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static org.example.config.gson.GsonConfig.GSON;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaUserListener {

    private final UserService userService;

    @KafkaListener(topics = "#{kafkaTopicConfig.getUserRegisterTopic()}")
    public void createUserFromRegisterMessage(String registerMessage) {
        log.info("received register message {}", registerMessage);
        RegisterDto registerDto = GSON.fromJson(registerMessage, RegisterDto.class);
        userService.createUserFromRegistrationDto(registerDto);
    }
}
