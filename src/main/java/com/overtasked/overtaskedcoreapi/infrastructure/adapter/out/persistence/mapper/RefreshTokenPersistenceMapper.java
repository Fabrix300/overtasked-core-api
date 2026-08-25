package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.mapper;

import com.overtasked.overtaskedcoreapi.domain.model.RefreshToken;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.RefreshTokenEntity;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenPersistenceMapper {

    public RefreshTokenEntity toEntity(RefreshToken token) {
        RefreshTokenEntity entity = new RefreshTokenEntity();

        entity.setId(token.getId());
        entity.setUserId(token.getUserId());
        entity.setTokenHash(token.getTokenHash());
        entity.setExpiresAt(token.getExpiresAt());
        entity.setRevokedAt(token.getRevokedAt());
        entity.setCreatedAt(token.getCreatedAt());

        return entity;
    }

    public RefreshToken toDomain(RefreshTokenEntity entity) {
        return RefreshToken.reconstitute(
                entity.getId(),
                entity.getUserId(),
                entity.getTokenHash(),
                entity.getExpiresAt(),
                entity.getCreatedAt(),
                entity.getRevokedAt()
        );
    }

}
