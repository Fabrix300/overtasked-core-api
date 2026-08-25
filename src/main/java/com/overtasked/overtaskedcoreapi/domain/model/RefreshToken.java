package com.overtasked.overtaskedcoreapi.domain.model;

import java.time.Instant;
import java.util.UUID;

public class RefreshToken {

    private UUID id;
    private UUID userId;
    private String tokenHash;

    private Instant expiresAt;
    private Instant createdAt;

    private Instant revokedAt;

    public RefreshToken(
            UUID id,
            UUID userId,
            String tokenHash,
            Instant expiresAt,
            Instant createdAt,
            Instant revokedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.tokenHash = tokenHash;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.revokedAt = revokedAt;
    }

    public static RefreshToken create(
            UUID userId,
            String tokenHash,
            Instant expiresAt,
            Instant now
    ) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        if (tokenHash == null || tokenHash.isBlank()) {
            throw new IllegalArgumentException("Token hash cannot be blank");
        }

        if (expiresAt == null || !expiresAt.isAfter(now)) {
            throw new IllegalArgumentException(
                    "Refresh token expiration must be in the future"
            );
        }

        return new RefreshToken(
                UUID.randomUUID(),
                userId,
                tokenHash,
                expiresAt,
                now,
                null
        );
    }

    public static RefreshToken reconstitute(
            UUID id,
            UUID userId,
            String tokenHash,
            Instant expiresAt,
            Instant createdAt,
            Instant revokedAt
    ) {
        return new RefreshToken(
                id,
                userId,
                tokenHash,
                expiresAt,
                createdAt,
                revokedAt
        );
    }

    public boolean isExpired(Instant now) {
        return !expiresAt.isAfter(now);
    }

    public boolean isRevoked() {
        return revokedAt != null;
    }

    public boolean isValid(Instant now) {
        return !isRevoked() && !isExpired(now);
    }

    public void revoke(Instant now) {
        if (isRevoked()) {
            return;
        }

        this.revokedAt = now;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getRevokedAt() {
        return revokedAt;
    }

    public void setRevokedAt(Instant revokedAt) {
        this.revokedAt = revokedAt;
    }
}
