package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence;

import com.overtasked.overtaskedcoreapi.domain.model.User;
import com.overtasked.overtaskedcoreapi.domain.port.out.UserRepository;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository repository;
    private final UserPersistenceMapper mapper;

    public UserRepositoryAdapter(
            UserJpaRepository repository,
            UserPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findById(UUID userId) {
        Optional<UserEntity> userEntity = repository.findById(userId);

        return userEntity.map(mapper::toDomain);
    }
}
