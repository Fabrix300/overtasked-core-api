package com.overtasked.overtaskedcoreapi.domain.port.out;

import com.overtasked.overtaskedcoreapi.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findById(UUID userId);

}
