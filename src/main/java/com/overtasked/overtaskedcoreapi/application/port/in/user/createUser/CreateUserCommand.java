package com.overtasked.overtaskedcoreapi.application.port.in.user.createUser;

public record CreateUserCommand(
        String email,
        String password,
        String name
) { }
