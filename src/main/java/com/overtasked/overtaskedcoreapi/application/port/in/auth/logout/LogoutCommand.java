package com.overtasked.overtaskedcoreapi.application.port.in.auth.logout;

public record LogoutCommand(
        String refreshToken
) { }