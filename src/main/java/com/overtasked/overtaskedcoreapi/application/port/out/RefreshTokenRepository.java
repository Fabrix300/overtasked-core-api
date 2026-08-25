package com.overtasked.overtaskedcoreapi.application.port.out;

import com.overtasked.overtaskedcoreapi.domain.model.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByTokenHash(String tokenHash);

    Optional<RefreshToken> findById(UUID id);

    void delete(RefreshToken refreshToken);

    void revokeAllByUserId(UUID userId);

}
