package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.mapper;

import com.overtasked.overtaskedcoreapi.domain.model.user.Email;
import com.overtasked.overtaskedcoreapi.domain.model.user.PasswordHash;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();

        entity.setId(user.getId());
        entity.setEmail(user.getEmail().value());
        entity.setPasswordHash(user.getPasswordHash().value());
        entity.setName(user.getName());
        entity.setStatus(user.getStatus());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());

        return entity;
    }

    public User toDomain(UserEntity userEntity) {
        return User.reconstitute(
                userEntity.getId(),
                new Email(userEntity.getEmail()),
                new PasswordHash(userEntity.getPasswordHash()),
                userEntity.getName(),
                userEntity.getStatus(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
        );
    }

}
