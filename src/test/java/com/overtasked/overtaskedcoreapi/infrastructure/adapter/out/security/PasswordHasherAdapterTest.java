package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.security;

import com.overtasked.overtaskedcoreapi.domain.model.user.PasswordHash;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordHasherAdapterTest {

    private static final String PLAIN_PASSWORD = "mjlives4ev3r";

    private final PasswordHasherAdapter passwordHasherAdapter = new PasswordHasherAdapter();

    @Test
    void shouldHashPassword() {
        // Given
        String password = PLAIN_PASSWORD;

        // When
        PasswordHash hashedPassword = passwordHasherAdapter.hash(password);

        // Then
        assertThat(hashedPassword.value())
                .isNotEqualTo(password);

        assertThat(hashedPassword.value())
                .isNotBlank();
    }

    @Test
    void shouldMatchCorrectPassword() {
        // Given
        String password = PLAIN_PASSWORD;

        PasswordHash hashedPassword = passwordHasherAdapter.hash(password);

        // When
        boolean matches = passwordHasherAdapter.matches(
                password,
                hashedPassword
        );

        // Then
        assertThat(matches).isTrue();
    }

    @Test
    void shouldNotMatchIncorrectPassword() {
        // Given
        String password = PLAIN_PASSWORD;

        PasswordHash hashedPassword = passwordHasherAdapter.hash(password);

        // When
        boolean matches = passwordHasherAdapter.matches(
                password + "wrong",
                hashedPassword
        );

        // Then
        assertThat(matches).isFalse();
    }

}
