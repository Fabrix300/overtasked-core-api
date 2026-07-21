package com.overtasked.overtaskedcoreapi.domain.exception;

public class UserNotProjectMemberException extends RuntimeException {
    public UserNotProjectMemberException(String message) {
        super(message);
    }
}
