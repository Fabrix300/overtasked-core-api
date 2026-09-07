package com.overtasked.overtaskedcoreapi.application.port.in.auth.login;

public record AuthenticationResult(
        String accessToken,
        String refreshToken,
        long expiresIn
) {
}
