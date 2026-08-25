package com.overtasked.overtaskedcoreapi.application.port.in.task.createTask;

public interface CreateTaskUseCase {

    CreateTaskResult execute(CreateTaskCommand command);

}
