package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.security;

import com.overtasked.overtaskedcoreapi.application.port.out.auth.GeneratedRefreshToken;
import com.overtasked.overtaskedcoreapi.application.port.out.auth.RefreshTokenGenerator;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.shared.SystemClockAdapter;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HexFormat;

@Component
public class RefreshTokenGeneratorAdapter implements RefreshTokenGenerator {

    private final SecureRandom secureRandom = new SecureRandom();

    private final SystemClockAdapter clock;

    public RefreshTokenGeneratorAdapter(
            SystemClockAdapter clock
    ) {
        this.clock = clock;
    }

    @Override
    public GeneratedRefreshToken generateRefreshToken(User user) {
        byte[] randomBytes = new byte[64];

        secureRandom.nextBytes(randomBytes);

        String rawToken = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);
        String tokenHash = hash(rawToken);
        Instant expiresAt = clock.now().plus(30, ChronoUnit.DAYS); // TODO: Make amount a variable, maybe .env

        return new GeneratedRefreshToken(
                rawToken,
                tokenHash,
                expiresAt
        );
    }

    @Override
    public String hash(String rawToken) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    rawToken.getBytes(StandardCharsets.UTF_8)
            );

            return HexFormat.of().formatHex(hash);

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }

}
