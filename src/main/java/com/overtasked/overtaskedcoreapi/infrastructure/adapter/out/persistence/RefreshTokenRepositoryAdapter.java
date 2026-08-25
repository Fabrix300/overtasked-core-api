package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence;

import com.overtasked.overtaskedcoreapi.application.port.out.RefreshTokenRepository;
import com.overtasked.overtaskedcoreapi.application.port.out.shared.Clock;
import com.overtasked.overtaskedcoreapi.domain.model.RefreshToken;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.RefreshTokenEntity;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.mapper.RefreshTokenPersistenceMapper;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.repository.RefreshTokenJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository repository;
    private final RefreshTokenPersistenceMapper mapper;
    private final Clock clock;

    public RefreshTokenRepositoryAdapter(
            RefreshTokenJpaRepository repository,
            RefreshTokenPersistenceMapper mapper,
            Clock clock
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.clock = clock;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity entity = mapper.toEntity(refreshToken);
        RefreshTokenEntity saved = repository.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<RefreshToken> findByTokenHash(String tokenHash) {
        return repository
                .findByTokenHash(tokenHash)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<RefreshToken> findById(UUID id) {
        return repository
                .findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        repository.delete(mapper.toEntity(refreshToken));
    }

    @Override
    @Transactional
    public void revokeAllByUserId(UUID userId) {
        repository.revokeAllByUserId(
                userId,
                clock.now()
        );
    }
}
