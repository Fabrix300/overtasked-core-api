package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.security;

import com.overtasked.overtaskedcoreapi.application.port.out.auth.PasswordHasher;
import com.overtasked.overtaskedcoreapi.domain.model.user.PasswordHash;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasherAdapter implements PasswordHasher {

    private final BCryptPasswordEncoder encoder;

    public PasswordHasherAdapter() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public PasswordHash hash(String rawPassword) {
        return new PasswordHash(encoder.encode(rawPassword));
    }

    @Override
    public boolean matches(String rawPassword, PasswordHash passwordHash) {
        return encoder.matches(rawPassword, passwordHash.value());
    }

}
