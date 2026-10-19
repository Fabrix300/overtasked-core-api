package com.overtasked.overtaskedcoreapi.application.service;

import com.overtasked.overtaskedcoreapi.application.exception.UserNotFoundException;
import com.overtasked.overtaskedcoreapi.application.port.in.user.createUser.UserResult;
import com.overtasked.overtaskedcoreapi.application.port.in.user.getUser.GetUserQuery;
import com.overtasked.overtaskedcoreapi.application.port.out.UserRepository;
import com.overtasked.overtaskedcoreapi.domain.enums.UserStatus;
import com.overtasked.overtaskedcoreapi.domain.model.user.Email;
import com.overtasked.overtaskedcoreapi.domain.model.user.PasswordHash;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserServiceTest {

    private static final String NAME = "Michael Jackson";
    private static final String EMAIL = "michael@example.com";
    // private static final String PLAIN_PASSWORD = "mjlives4ev3r";
    private static final String HASHED_PASSWORD = "$argon2id$v=19$...";

    @Mock
    private UserRepository userRepository;

    private GetUserService getUserService;

    @BeforeEach
    void setUp() {
        getUserService = new GetUserService(
                userRepository
        );
    }

    @Test
    void shouldReturnUserWhenUserExists() {
        // Arrange
        UUID userId = UUID.randomUUID();

        User user = createTestUser(userId);

        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(user));

        GetUserQuery query = new GetUserQuery(userId);

        // Act
        UserResult result = getUserService.execute(query);

        // Assert
        assertEquals(userId, result.id());
        assertEquals(user.getEmail().value(), result.email());
        assertEquals(user.getName(), result.name());

        verify(userRepository).findById(any(UUID.class));
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        GetUserQuery query = new GetUserQuery(userId);

        // Act & Assert
        assertThrows(
                UserNotFoundException.class,
                () -> getUserService.execute(query)
        );
    }

    @Test
    void shouldNotExposePasswordHash() {
        // Arrange
        UUID userId = UUID.randomUUID();

        User user = createTestUser(userId);

        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(user));

        GetUserQuery query = new GetUserQuery(userId);

        // Act
        UserResult result = getUserService.execute(query);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.id());

        // GetUserResult no declara ningún campo de contraseña.
        assertFalse(
                Arrays.stream(UserResult.class.getRecordComponents())
                        .anyMatch(component ->
                                component.getName().toLowerCase().contains("password")
                        )
        );
    }

    private User createTestUser(UUID userId) {
        return new User(
                userId,
                new Email(EMAIL),
                new PasswordHash(HASHED_PASSWORD),
                NAME,
                UserStatus.ACTIVE,
                Instant.now(),
                Instant.now()
        );
    }

}
