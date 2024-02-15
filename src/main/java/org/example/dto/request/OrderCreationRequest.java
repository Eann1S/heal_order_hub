package org.example.dto.request;

public record OrderCreationRequest(
        String ownerId,
        String text,
        int serviceCost
) {
}
