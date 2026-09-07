package com.overtasked.overtaskedcoreapi.application.service;

import com.overtasked.overtaskedcoreapi.application.exception.InvalidRefreshTokenException;
import com.overtasked.overtaskedcoreapi.application.exception.UserInactiveException;
import com.overtasked.overtaskedcoreapi.application.port.in.auth.login.AuthenticationResult;
import com.overtasked.overtaskedcoreapi.application.port.in.auth.refreshAccessToken.RefreshAccessTokenCommand;
import com.overtasked.overtaskedcoreapi.application.port.in.auth.refreshAccessToken.RefreshAccessTokenUseCase;
import com.overtasked.overtaskedcoreapi.application.port.out.RefreshTokenRepository;
import com.overtasked.overtaskedcoreapi.application.port.out.UserRepository;
import com.overtasked.overtaskedcoreapi.application.port.out.auth.AccessToken;
import com.overtasked.overtaskedcoreapi.application.port.out.auth.AccessTokenGenerator;
import com.overtasked.overtaskedcoreapi.application.port.out.auth.GeneratedRefreshToken;
import com.overtasked.overtaskedcoreapi.application.port.out.auth.RefreshTokenGenerator;
import com.overtasked.overtaskedcoreapi.application.port.out.shared.Clock;
import com.overtasked.overtaskedcoreapi.domain.model.RefreshToken;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Service
@Transactional
public class RefreshAccessTokenService implements RefreshAccessTokenUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final RefreshTokenGenerator refreshTokenGenerator;
    private final AccessTokenGenerator accessTokenGenerator;
    private final Clock clock;

    public RefreshAccessTokenService(
            RefreshTokenRepository refreshTokenRepository,
            UserRepository userRepository,
            RefreshTokenGenerator refreshTokenGenerator,
            AccessTokenGenerator accessTokenGenerator,
            Clock clock
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.refreshTokenGenerator = refreshTokenGenerator;
        this.accessTokenGenerator = accessTokenGenerator;
        this.clock = clock;
    }

    @Override
    public AuthenticationResult execute(
            RefreshAccessTokenCommand command
    ) {
        String tokenHash = refreshTokenGenerator.hash(command.refreshToken());

        RefreshToken refreshToken = refreshTokenRepository
                .findByTokenHash(tokenHash)
                .orElseThrow(InvalidRefreshTokenException::new);

        Instant now = clock.now();

        if (!refreshToken.isValid(now)) {
            throw new InvalidRefreshTokenException();
        }

        User user = userRepository
                .findById(refreshToken.getUserId())
                .orElseThrow(() -> {
                    // TODO: This error and log can be improved?...
                    log.info("User not found with provided ID in token.");

                    return new InvalidRefreshTokenException();
                });

        if (!user.isActive()) {
            throw new UserInactiveException(user.getId());
        }

        // Revoke and save revoked token (We create a new one as part of "Refresh Token Rotation" good practice)
        refreshToken.revoke(now);
        refreshTokenRepository.save(refreshToken);

        AccessToken generatedAccessToken = accessTokenGenerator.generateAccessToken(user);
        GeneratedRefreshToken generatedRefreshToken = refreshTokenGenerator.generateRefreshToken(user);

        // Saving the new refresh token
        RefreshToken rotatedRefreshToken = RefreshToken.create(
                user.getId(),
                generatedRefreshToken.tokenHash(),
                generatedRefreshToken.expiresAt(),
                now
        );
        refreshTokenRepository.save(rotatedRefreshToken);

        return new AuthenticationResult(
                generatedAccessToken.value(),
                generatedRefreshToken.rawToken(),
                Duration.between(now, generatedAccessToken.expiresAt()).toSeconds()
        );
    }
}