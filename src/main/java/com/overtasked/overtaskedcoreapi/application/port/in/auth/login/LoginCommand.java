package com.overtasked.overtaskedcoreapi.application.port.in.auth.login;

public record LoginCommand(
        String email,
        String password
) { }
