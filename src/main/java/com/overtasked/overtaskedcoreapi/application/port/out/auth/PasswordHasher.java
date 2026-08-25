package com.overtasked.overtaskedcoreapi.application.port.out.auth;

import com.overtasked.overtaskedcoreapi.domain.model.user.PasswordHash;

public interface PasswordHasher {

    PasswordHash hash(String rawPassword);

    boolean matches(
            String rawPassword,
            PasswordHash passwordHash
    );

}
