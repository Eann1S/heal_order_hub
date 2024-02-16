package org.example.dto;

public record OrderDto(
        String id,
        UserDto owner,
        String text,
        int serviceCost
) {
}
