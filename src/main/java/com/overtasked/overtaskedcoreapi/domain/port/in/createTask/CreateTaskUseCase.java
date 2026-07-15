package com.overtasked.overtaskedcoreapi.domain.port.in.createTask;

public interface CreateTaskUseCase {

    CreateTaskResult execute(CreateTaskCommand command);

}
