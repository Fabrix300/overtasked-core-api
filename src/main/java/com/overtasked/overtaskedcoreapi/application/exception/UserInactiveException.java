package com.overtasked.overtaskedcoreapi.application.exception;

import java.util.UUID;

public class UserInactiveException extends RuntimeException {
    public UserInactiveException(UUID id) {
        super("User (" + id + ") is not inactive");
    }
}
