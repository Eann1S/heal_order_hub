package org.example.dto.request;

import java.util.Map;

public record ContactsUpdateRequest(
        Map<String, String> contactsMap
) {
}
