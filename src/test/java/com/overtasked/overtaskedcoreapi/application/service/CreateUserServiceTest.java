package com.overtasked.overtaskedcoreapi.application.service;

import com.overtasked.overtaskedcoreapi.application.exception.EmailAlreadyRegisteredException;
import com.overtasked.overtaskedcoreapi.application.port.in.user.createUser.CreateUserCommand;
import com.overtasked.overtaskedcoreapi.application.port.out.UserRepository;
import com.overtasked.overtaskedcoreapi.application.port.out.auth.PasswordHasher;
import com.overtasked.overtaskedcoreapi.application.port.out.shared.Clock;
import com.overtasked.overtaskedcoreapi.domain.model.user.Email;
import com.overtasked.overtaskedcoreapi.domain.model.user.PasswordHash;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserServiceTest {

    private static final String NAME = "Michael Jackson";
    private static final String EMAIL = "michael@example.com";
    private static final String PLAIN_PASSWORD = "mjlives4ev3r";
    private static final String HASHED_PASSWORD = "$argon2id$v=19$...";

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordHasher passwordHasher;

    @Mock
    private Clock clock;

    private CreateUserService createUserService;

    @BeforeEach
    void setUp() {
        createUserService = new CreateUserService(
                userRepository,
                passwordHasher,
                clock);
    }

    @Test
    void shouldCreateUserWhenEmailIsAvailable() {
        // Given
        var command = new CreateUserCommand(
                EMAIL,
                PLAIN_PASSWORD,
                NAME
        );

        when(userRepository.existsByEmail(new Email(EMAIL)))
                .thenReturn(false);

        when(passwordHasher.hash(PLAIN_PASSWORD))
                .thenReturn(new PasswordHash(HASHED_PASSWORD));

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        var result = createUserService.execute(command);

        // Then
        assertThat(result).isNotNull();

        verify(userRepository).existsByEmail(new Email(EMAIL));

        verify(passwordHasher).hash(PLAIN_PASSWORD);

        verify(userRepository).save(argThat(user ->
                user.getEmail().value().equals(EMAIL)
                        && user.getPasswordHash().value().equals(HASHED_PASSWORD)
        ));
    }

    @Test
    void shouldRejectCreationWhenEmailAlreadyExists() {
        // Given
        var command = new CreateUserCommand(
                EMAIL,
                PLAIN_PASSWORD,
                NAME
        );

        when(userRepository.existsByEmail(new Email(EMAIL)))
                .thenReturn(true);

        // When / Then
        assertThatThrownBy(() -> createUserService.execute(command))
                .isInstanceOf(EmailAlreadyRegisteredException.class);

        verify(userRepository).existsByEmail(new Email(EMAIL));

        verify(passwordHasher, never())
                .hash(any());

        verify(userRepository, never())
                .save(any(User.class));
    }


    @Test
    void shouldHashPasswordBeforeSavingUser() {
        // Given
        var command = new CreateUserCommand(
                EMAIL,
                PLAIN_PASSWORD,
                NAME
        );

        when(passwordHasher.hash(PLAIN_PASSWORD))
                .thenReturn(new PasswordHash(HASHED_PASSWORD));

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        createUserService.execute(command);

        // Then
        verify(passwordHasher).hash(PLAIN_PASSWORD);

        var userCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userCaptor.capture());

        var savedUser = userCaptor.getValue();

        assertThat(savedUser.getPasswordHash().value())
                .isEqualTo(HASHED_PASSWORD);

        assertThat(savedUser.getPasswordHash().value())
                .isNotEqualTo(PLAIN_PASSWORD);
    }

}
