package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.dto.request.ContactsUpdateRequest;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        UserDto userDto = userService.getUserDtoById(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUserContacts(
            @PathVariable String id,
            @RequestBody ContactsUpdateRequest contactsUpdateRequest
    ) {
        userService.updateUserContactsById(id, contactsUpdateRequest);
        UserDto userDto = userService.getUserDtoById(id);
        return ResponseEntity.ok(userDto);
    }
}
