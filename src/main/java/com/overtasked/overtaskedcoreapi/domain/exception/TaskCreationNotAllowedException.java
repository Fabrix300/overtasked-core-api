package com.overtasked.overtaskedcoreapi.domain.exception;

public class TaskCreationNotAllowedException extends RuntimeException {
    public TaskCreationNotAllowedException(String message) {
        super(message);
    }
}
