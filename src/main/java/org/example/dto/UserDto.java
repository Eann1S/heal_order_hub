package org.example.dto;

import java.util.Map;

public record UserDto(
        String id,
        String email,
        String username,
        Map<String, String> contactsMap
) {
}
