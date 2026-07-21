package com.overtasked.overtaskedcoreapi.infrastructure.adapter.in.web.request;

import com.overtasked.overtaskedcoreapi.domain.enums.TaskPriority;
import com.overtasked.overtaskedcoreapi.domain.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTaskRequest(
        String title,
        String description,
        UUID creatorId,
        UUID assigneeId,
        TaskStatus status,
        TaskPriority priority,
        LocalDateTime dueDate
){}
