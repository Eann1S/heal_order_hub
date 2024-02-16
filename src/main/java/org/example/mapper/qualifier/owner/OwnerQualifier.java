package org.example.mapper.qualifier.owner;

import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerQualifier {

    private final UserService userService;

    @OwnerById
    public User findOwnerById(String id) {
        return userService.findUserByIdInDatabase(id);
    }
}
