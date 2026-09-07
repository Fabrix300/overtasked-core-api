package com.overtasked.overtaskedcoreapi.application.port.out.auth;

import java.time.Instant;

public record AccessToken(
        String value,
        Instant expiresAt
) {
}
