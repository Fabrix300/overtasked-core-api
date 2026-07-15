package com.overtasked.overtaskedcoreapi.domain.port.in.createTask;

import com.overtasked.overtaskedcoreapi.domain.enums.TaskStatus;

public record CreateTaskResult(
        Long taskId,
        String title,
        TaskStatus status
){}