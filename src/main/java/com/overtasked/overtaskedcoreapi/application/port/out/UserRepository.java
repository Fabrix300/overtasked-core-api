package com.overtasked.overtaskedcoreapi.application.port.out;

import com.overtasked.overtaskedcoreapi.domain.model.user.Email;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID userId);

    Optional<User> findByEmail(Email email);

    boolean existsByEmail(Email email);

    void delete(User user);

}
