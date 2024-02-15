package org.example.exception;

import static org.example.message.ErrorMessage.USER_NOT_FOUND;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userProperty) {
        super(USER_NOT_FOUND.formatWith(userProperty));
    }
}
