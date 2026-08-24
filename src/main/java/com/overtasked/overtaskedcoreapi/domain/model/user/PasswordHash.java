package com.overtasked.overtaskedcoreapi.domain.model.user;

public record PasswordHash(String value) {

    public PasswordHash {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password hash cannot be blank");
        }
    }

}
