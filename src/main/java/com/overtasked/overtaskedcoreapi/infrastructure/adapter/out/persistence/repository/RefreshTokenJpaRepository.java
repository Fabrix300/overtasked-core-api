package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.repository;

import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, UUID> {

    Optional<RefreshTokenEntity> findByTokenHash(
            String tokenHash
    );

    @Modifying
    @Query("""
        UPDATE RefreshTokenJpaEntity r
           SET r.revokedAt = :revokedAt
         WHERE r.userId = :userId
           AND r.revokedAt IS NULL
    """)
    void revokeAllByUserId(
            UUID userId,
            Instant revokedAt
    );

}
