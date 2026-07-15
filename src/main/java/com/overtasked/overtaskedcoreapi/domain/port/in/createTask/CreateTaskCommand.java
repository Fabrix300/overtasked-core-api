package com.overtasked.overtaskedcoreapi.domain.port.in.createTask;

import com.overtasked.overtaskedcoreapi.domain.enums.TaskPriority;

public record CreateTaskCommand(
        Long projectId,
        Long creatorId,
        String title,
        String description,
        TaskPriority priority,
        Long assigneeId
) {}
