package com.overtasked.overtaskedcoreapi.domain.model.user;

import com.overtasked.overtaskedcoreapi.domain.enums.UserStatus;

import java.time.Instant;
import java.util.UUID;

public class User {

    private final UUID id;

    private Email email;
    private PasswordHash passwordHash;
    private String name;
    private UserStatus status;

    private Instant createdAt;
    private Instant updatedAt;

    public User(
            UUID id,
            Email email,
            PasswordHash passwordHash,
            String name,
            UserStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User create(
            Email email,
            PasswordHash passwordHash,
            String name,
            Instant now
    ) {
        validateName(name);

        return new User(
                UUID.randomUUID(),
                email,
                passwordHash,
                name.trim(),
                UserStatus.ACTIVE,
                now,
                now
        );
    }

    public void changeEmail(Email email, Instant now) {
        this.email = email;
        this.updatedAt = now;
    }

    public void changeName(String name, Instant now) {
        validateName(name);

        this.name = name.trim();
        this.updatedAt = now;
    }

    public void updatePasswordHash(
            PasswordHash passwordHash,
            Instant now
    ) {
        this.passwordHash = passwordHash;
        this.updatedAt = now;
    }

    public void activate(Instant now) {
        if (this.status == UserStatus.ACTIVE) {
            return;
        }

        this.status = UserStatus.ACTIVE;
        this.updatedAt = now;
    }

    public void deactivate(Instant now) {
        if (this.status == UserStatus.INACTIVE) {
            return;
        }

        this.status = UserStatus.INACTIVE;
        this.updatedAt = now;
    }

    public boolean isActive() {
        return this.status == UserStatus.ACTIVE;
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be blank");
        }
    }

    public UUID getId() {
        return id;
    }
}
