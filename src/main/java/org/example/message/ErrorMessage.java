package org.example.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    USER_NOT_FOUND("User with property %s was not found"),
    ORDER_NOT_FOUND("Order with property %s was not found");

    private final String message;

    public String formatWith(Object... params) {
        return message.formatted(params);
    }
}
