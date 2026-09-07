package com.overtasked.overtaskedcoreapi.application.service;

import com.overtasked.overtaskedcoreapi.application.exception.InvalidCredentialsException;
import com.overtasked.overtaskedcoreapi.application.exception.UserInactiveException;
import com.overtasked.overtaskedcoreapi.application.port.in.user.login.AuthenticationResult;
import com.overtasked.overtaskedcoreapi.application.port.in.user.login.LoginCommand;
import com.overtasked.overtaskedcoreapi.application.port.in.user.login.LoginUseCase;
import com.overtasked.overtaskedcoreapi.application.port.out.RefreshTokenRepository;
import com.overtasked.overtaskedcoreapi.application.port.out.UserRepository;
import com.overtasked.overtaskedcoreapi.application.port.out.auth.*;
import com.overtasked.overtaskedcoreapi.application.port.out.shared.Clock;
import com.overtasked.overtaskedcoreapi.domain.model.RefreshToken;
import com.overtasked.overtaskedcoreapi.domain.model.user.Email;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;

@Service
@Transactional
public class LoginService implements LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final AccessTokenGenerator accessTokenGenerator;
    private final RefreshTokenGenerator refreshTokenGenerator;
    private final RefreshTokenRepository refreshTokenRepository;
    private final Clock clock;

    public LoginService(
            UserRepository userRepository,
            PasswordHasher passwordHasher,
            AccessTokenGenerator accessTokenGenerator,
            RefreshTokenGenerator refreshTokenGenerator,
            RefreshTokenRepository refreshTokenRepository,
            Clock clock
    ) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.accessTokenGenerator = accessTokenGenerator;
        this.refreshTokenGenerator = refreshTokenGenerator;
        this.refreshTokenRepository = refreshTokenRepository;
        this.clock = clock;
    }

    @Override
    public AuthenticationResult execute(LoginCommand command) {
        Email email = new Email(command.email());

        User user = userRepository.findByEmail(email).orElseThrow(InvalidCredentialsException::new);

        if (!user.isActive()) {
            throw new UserInactiveException(user.getId());
        }

        if (!passwordHasher.matches(command.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        AccessToken accessToken = accessTokenGenerator.generateAccessToken(user);

        GeneratedRefreshToken generated = refreshTokenGenerator.generateRefreshToken(user);

        Instant now = clock.now();

        RefreshToken refreshToken = RefreshToken.create(
                user.getId(),
                generated.tokenHash(),
                generated.expiresAt(),
                now
        );

        refreshTokenRepository.save(refreshToken);

        return new AuthenticationResult(
                accessToken.value(),
                generated.rawToken(),
                Duration.between(now, accessToken.expiresAt()).toSeconds()
        );
    }

}
