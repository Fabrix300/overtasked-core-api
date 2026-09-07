package com.overtasked.overtaskedcoreapi.application.service;

import com.overtasked.overtaskedcoreapi.application.exception.EmailAlreadyRegisteredException;
import com.overtasked.overtaskedcoreapi.application.port.in.user.createUser.CreateUserCommand;
import com.overtasked.overtaskedcoreapi.application.port.in.user.createUser.CreateUserUseCase;
import com.overtasked.overtaskedcoreapi.application.port.in.user.createUser.UserResult;
import com.overtasked.overtaskedcoreapi.application.port.out.UserRepository;
import com.overtasked.overtaskedcoreapi.application.port.out.auth.PasswordHasher;
import com.overtasked.overtaskedcoreapi.application.port.out.shared.Clock;
import com.overtasked.overtaskedcoreapi.domain.model.user.Email;
import com.overtasked.overtaskedcoreapi.domain.model.user.PasswordHash;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final Clock clock;

    public CreateUserService(
            UserRepository userRepository,
            PasswordHasher passwordHasher,
            Clock clock
    ) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.clock = clock;
    }

    @Override
    public UserResult execute(CreateUserCommand command) {
        Email email = new Email(command.email());

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException(email.value());
        }

        PasswordHash passwordHash = passwordHasher.hash(command.password());
        Instant now = clock.now();

        User user = User.create(
                email,
                passwordHash,
                command.name(),
                now
        );
        User savedUser = userRepository.save(user);

        return toResult(savedUser);
    }

    private UserResult toResult(User user) {
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
