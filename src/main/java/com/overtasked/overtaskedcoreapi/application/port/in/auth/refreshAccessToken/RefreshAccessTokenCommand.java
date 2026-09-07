package com.overtasked.overtaskedcoreapi.application.port.in.auth.refreshAccessToken;

public record RefreshAccessTokenCommand(
        String refreshToken
) { }
