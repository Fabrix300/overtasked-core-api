package com.overtasked.overtaskedcoreapi.domain.model;

import java.time.Instant;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private Instant createdAt;
    private Instant updatedAt;

    public User(
            UUID id,
            String name,
            String email,
            String password
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
