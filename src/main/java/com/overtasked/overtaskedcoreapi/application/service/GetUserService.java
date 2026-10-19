package com.overtasked.overtaskedcoreapi.application.service;

import com.overtasked.overtaskedcoreapi.application.exception.UserNotFoundException;
import com.overtasked.overtaskedcoreapi.application.port.in.user.createUser.UserResult;
import com.overtasked.overtaskedcoreapi.application.port.in.user.getUser.GetUserQuery;
import com.overtasked.overtaskedcoreapi.application.port.in.user.getUser.GetUserUseCase;
import com.overtasked.overtaskedcoreapi.application.port.out.UserRepository;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import org.springframework.stereotype.Service;

// TODO: Consider the use of authentication
@Service
public class GetUserService implements GetUserUseCase {

    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResult execute(GetUserQuery query) {
        User user = userRepository.findById(query.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new UserResult(
                user.getId(),
                user.getEmail().value(),
                user.getName(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
