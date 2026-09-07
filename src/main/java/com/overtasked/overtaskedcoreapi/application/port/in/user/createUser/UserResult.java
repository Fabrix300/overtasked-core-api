package com.overtasked.overtaskedcoreapi.application.port.in.user.createUser;

import com.overtasked.overtaskedcoreapi.domain.enums.UserStatus;

import java.time.Instant;
import java.util.UUID;

public record UserResult(
        UUID id,
        String email,
        String name,
        UserStatus status,
        Instant createdAt,
        Instant updatedAt
) { }
