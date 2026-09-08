package com.overtasked.overtaskedcoreapi.application.service;

import com.overtasked.overtaskedcoreapi.application.port.in.auth.logout.LogoutCommand;
import com.overtasked.overtaskedcoreapi.application.port.in.auth.logout.LogoutUseCase;
import com.overtasked.overtaskedcoreapi.application.port.out.RefreshTokenRepository;
import com.overtasked.overtaskedcoreapi.application.port.out.auth.RefreshTokenGenerator;
import com.overtasked.overtaskedcoreapi.application.port.out.shared.Clock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogoutService implements LogoutUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenGenerator refreshTokenGenerator;
    private final Clock clock;

    public LogoutService(
            RefreshTokenRepository refreshTokenRepository,
            RefreshTokenGenerator refreshTokenGenerator,
            Clock clock
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenGenerator = refreshTokenGenerator;
        this.clock = clock;
    }

    @Override
    public void execute(LogoutCommand command) {
        String hash = refreshTokenGenerator.hash(command.refreshToken());

        refreshTokenRepository
                .findByTokenHash(hash)
                .ifPresent(token -> {
                    token.revoke(clock.now());
                    refreshTokenRepository.save(token);
                });
    }

}
