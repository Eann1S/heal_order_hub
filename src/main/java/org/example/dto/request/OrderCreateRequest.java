package org.example.dto.request;

public record OrderCreateRequest(
        String ownerId,
        String text,
        int serviceCost
) {
}
