package com.overtasked.overtaskedcoreapi.application.port.in.user.login;

public record LoginCommand(
        String email,
        String password
) {
}
