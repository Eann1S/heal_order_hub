package org.example.exception;

import static org.example.message.ErrorMessage.ORDER_NOT_FOUND;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Object orderProperty) {
        super(ORDER_NOT_FOUND.formatWith(orderProperty));
    }
}
