package org.example.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InfoMessage {

    ORDER_CREATED("Order was created successfully");

    private final String message;
}
