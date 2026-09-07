package com.overtasked.overtaskedcoreapi.application.port.in.user.login;

public record AuthenticationResult(
        String accessToken,
        String refreshToken,
        long expiresIn
) {
}
