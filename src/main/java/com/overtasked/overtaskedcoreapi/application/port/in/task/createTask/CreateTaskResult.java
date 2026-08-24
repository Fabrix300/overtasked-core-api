package com.overtasked.overtaskedcoreapi.application.port.in.task.createTask;

import com.overtasked.overtaskedcoreapi.domain.enums.TaskStatus;

import java.util.UUID;

public record CreateTaskResult(
        UUID taskId,
        String title,
        TaskStatus status
){}